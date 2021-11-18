package pro.enaza.gb.gbmobile_api.result

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?
}
