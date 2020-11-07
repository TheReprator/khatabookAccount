rootProject.name = "khatabookAccount"
//include("lib:error")
// Services
arrayOf(
    "account"
).forEach {
    include("$it:$it-service", "$it:$it-api")
}


// Libs
arrayOf(
    "error",
    "service"
).forEach {
    include("lib:$it")
}
