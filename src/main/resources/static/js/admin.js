function addProduct() {
    console.log(123);
}
function loadOrders() {
    const token = window.localStorage.getItem("JWT");
    const ordersContainer = document.querySelector("#orders");
    const result = document.querySelector("#result");
    const login = document.querySelector("#userLogin").value;
    ordersContainer.innerHTML = "";
    fetch('/api/v1/admin/orders', {
        method:"POST",
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json',"Authorization": `Bearer ${token}`},
        body:JSON.stringify({
            login:login
        })
    }).then(response => response.json())
        .then(orders => {

            if(Object.values(orders).length) {
                const products = Object.values(orders);
                console.log(products)
                Object.values(products).forEach((x)=>{

                    result.innerHTML += "Count:"+x.count+"\n";
                    result.innerHTML += "FlightId"+x.flightId+"\n";
                    result.innerHTML += "UserId"+x.userId+"\n";
                    result.innerHTML += "-----------------------"+"\n";
                })

            } else {
                result.innerHTML = "not found";
            }
        });
}
function loadFlights() {
    const token = window.localStorage.getItem("JWT");
    const result1 = document.querySelector("#resutproducts");
    fetch('/api/v1/admin/flights', {
            headers:{"Authorization":`Bearer ${token}`}}
        )
        .then(response => response.json())
        .then(orders => {

            if(Object.values(orders).length) {
                const products = Object.values(orders);
                console.log(products)
                Object.values(products).forEach((x)=>{

                    result1.innerHTML += "DateFrom:"+x.date_from+"\n";
                    result1.innerHTML += "DateTo:"+x.date_to+"\n";
                    result1.innerHTML += "FromPlace:"+x.from_place+"\n";
                    result1.innerHTML += "ToPlace:"+x.to_place+"\n";
                    result1.innerHTML += "Name:"+x.name+"\n";
                    result1.innerHTML += "Price:"+x.price+"\n";

                    result1.innerHTML += "-----------------------"+"\n";
                })

            } else {
                result1.innerHTML = "not found"
            }
        });
}
function loadUsers() {
    const token = window.localStorage.getItem("JWT");
    const result2 = document.querySelector("#resutuser");
    fetch('/api/v1/user/all', {
        headers:{"Authorization":`Bearer ${token}`}}
    )
        .then(response => response.json())
        .then(orders => {

            if(Object.values(orders).length) {
                const products = Object.values(orders);
                console.log(products)
                Object.values(products).forEach((x)=>{

                    result2.innerHTML += "Email:"+x.email+"\n";
                    result2.innerHTML += "Login:"+x.login+"\n";
                    result2.innerHTML += "Password:"+x.password+"\n";
                    result2.innerHTML += "-----------------------"+"\n";
                })

            } else {
                result2.innerHTML = "not found"
            }
        });
}
function addFlight() {
    const token = window.localStorage.getItem("JWT");
    const result3 = document.querySelector("#resultadd");

    const from_place = document.querySelector("#from").value;
    const to_place = document.querySelector("#to").value;
    const free = document.querySelector("#free_i").value;
    const price = document.querySelector("#price_i").value;
    const name = document.querySelector("#name_i").value;
    const date_from = document.querySelector("#datef_i").value;
    const date_to = document.querySelector("#datet_i").value;


    fetch('/api/v1/admin/flights/add', {
        method:"POST",
        body:JSON.stringify({
            date_from,
            date_to,
            from_place,
            to_place,
            free,
            price,
            name
        }),
        headers:{'Content-Type': 'application/json', 'Accept': 'application/json',"Authorization":`Bearer ${token}`}}
    )
        .then(response =>
        {

            if(response.status === 200) {
                result3.innerHTML = "Flight was created"

            } else {
                result3.innerHTML = "ERROR"
            }
        });
}
