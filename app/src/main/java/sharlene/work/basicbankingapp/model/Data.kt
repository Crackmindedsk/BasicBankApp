package sharlene.work.basicbankingapp.model

class Data {
    var name: String? = null
    var email: String? = null
    var sender: String? = null
    var reciever: String? = null
    var status: String? = null
    var balance: Int? = null

    constructor(name: String?, email: String?, balance: Int) {
        this.name = name
        this.email = email
        this.balance = balance
    }

    constructor(sender: String?, reciever: String?, balance: Int?, status: String?) {
        this.sender = sender
        this.reciever = reciever
        this.balance = balance
        this.status = status
    }
}