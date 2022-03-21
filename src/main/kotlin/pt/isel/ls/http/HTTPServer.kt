package pt.isel.ls.http

import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Status.Companion.CREATED
import org.http4k.routing.path

@Serializable
data class Student(val name : String, val number : Int)

val students = mutableListOf(
                    Student("Filipe", 10),
                    Student("Luis", 20),
                    Student("Daniel", 30))

fun getStudents(request : Request) : Response {
    printRequest(request)
    val limit = request.query("limit")?.toInt()?:2
    return Response(OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(students.take(limit)))
}

fun getStudent(request : Request) : Response {
    printRequest(request)
    val stdNumber = request.path("number")?.toInt()
    return Response(OK)
            .header("content-type", "application/json")
            .body(Json.encodeToString(students.find { it.number == stdNumber }))
}

fun postStudent(request : Request) : Response {
    printRequest(request)
    val std = Json.decodeFromString<Student>(request.bodyString())
    students.add(std)
    return Response(CREATED)
            .header("content-type", "application/json")
            .body(Json.encodeToString(std))
}

fun printRequest(request : Request){
    println("Method ${request.method}")
    println("Uri ${request.uri}")
    println("Header content-type ${request.header("content-type")}")
    println("Header accept ${request.header("accept")}")
}

fun main() {

    val app = routes(
        "students" bind GET to ::getStudents,
        "students/{number}" bind GET to ::getStudent,
        "students" bind POST to ::postStudent
    )

    val jettyServer = app.asServer(Jetty(9000)).start()

    readln()
    jettyServer.stop()

}