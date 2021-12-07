function Buy() {
    const token = window.localStorage.getItem("JWT");
    const accountId = window.localStorage.getItem("accountId");
    if (token === null){
        console.log(11111111)
        window.location.assign("/signin")
    }
    else {
        console.log(222222222)
        window.location.assign("/order")
    }
}