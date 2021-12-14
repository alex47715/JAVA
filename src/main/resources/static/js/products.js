const productsContainer = document.querySelector("#prod_container");
const ordersContainer = document.getElementsByTagName("body");

function loadProducts() {
    fetch("/api/v1/flight/all")
        .then(response => response.json())
        .then(data => {
            console.log(data)
            productsContainer.innerHTML = '';
            const products = Object.values(data);
            if(products.length > 0) {
                products.forEach(product => {
                    productsContainer.appendChild(productsComponent(product));
                })
            }
        });
}

function loadUsers() {
    const token = window.localStorage.getItem("JWT");
    const id = window.localStorage.getItem("accountId");
    const result2 = document.querySelector("#userInfo")
    fetch(`/api/v1/user/info?accountId=${id}`, {
        headers:{"Authorization":`Bearer ${token}`}}
    )
        .then(response => response.json())
        .then(user => {
            console.log(user)
                    // result2.innerHTML += "<div style='display: flex; flex-direction: column'>"
                    //     result2.innerHTML += "<div><h2 style='color: #007bff'>Email:</h2><h4>"+user.email+"</h4></div>";
                    //     result2.innerHTML += "<div><h2 style='color: #007bff'>Login:<h2><h4>"+user.login+"</h4></div>";
                    //     // result2.innerHTML += "<div><h2>Password:</h2><h4>"+user.password+"</h4></div>";
                    // result2.innerHTML += "</div>"

            result2.appendChild((() => {
                const div = document.createElement('div');
                div.style = "display: flex; flex-direction: column";
                div.appendChild((() => {
                    const p = document.createElement('p')
                    p.innerHTML = `Email: ${user.email}`;

                    return p;
                })());
                div.appendChild((() => {
                    const p = document.createElement('p')
                    p.innerHTML = `Login: ${user.login}`;

                    return p;
                })());
                div.appendChild((() => {
                    const p = document.createElement('h2')
                    p.innerHTML = `ORDERS:`;

                    return p;
                })());
                return div;
            })());

            user.orders.forEach(order => {
                result2.appendChild((() => {
                    const div = document.createElement('div');
                    div.style = "display: flex; flex-direction: column";
                    fetch(`/api/v1/flight/byid?id=${order.flightId}`)
                        .then(response => response.json())
                        .then(data => {
                            console.log(data)

                            div.appendChild((() => {
                                const p = document.createElement('p')
                                p.innerHTML = `Name:${data.name}`;
                                return p;
                            })());
                            div.appendChild((() => {
                                const p = document.createElement('p')
                                p.innerHTML = `DateFrom:${data.date_from}`;
                                return p;
                            })());
                            div.appendChild((() => {
                                const p = document.createElement('p')
                                p.innerHTML = `DateTo:${data.date_to}`;
                                return p;
                            })());
                            div.appendChild((() => {
                                const p = document.createElement('p')
                                p.innerHTML = `Price:${data.price}`;
                                return p;
                            })());
                            div.appendChild((() => {
                                const p = document.createElement('p')
                                p.innerHTML = `----------------------------------------------------`;
                                return p;
                            })());
                        });

                    return div;
                })());
            })
        });
}



function productsComponent(product) {
    const productContainer = document.createElement("div");
    // productContainer.classList.add("col-3");
    const card = document.createElement("div");
    card.classList.add("card");
    card.style.width="17rem";
    card.style.margin="0"
    card.style.border="gray 1px solid"
    const cardbody = document.createElement("div");
    cardbody.classList.add("card-body");
    cardbody.style.background="#87CEFA"
    const h5 = document.createElement("h5");
    h5.classList.add("card-title");
    h5.innerHTML = product.name;
    const p1 = document.createElement("p");
    p1.classList.add("card-text");
    p1.innerHTML = "From";
    const p2 = document.createElement("p");
    p2.classList.add("card-text");
    p2.innerHTML = new Date(product.date_from).toDateString() + ' ' + new Date(product.date_from).toTimeString();
    const p3 = document.createElement("p");
    p3.classList.add("card-text");
    p3.innerHTML = "To";
    const p4 = document.createElement("p");
    p4.classList.add("card-text");
    p4.innerHTML =  new Date(product.date_to).toDateString() + ' ' + new Date(product.date_to).toTimeString();;
    const p5 = document.createElement("p")
    p5.classList.add("card-text");
    p5.innerHTML = product.price+'$';
    const button = document.createElement("button");
    button.classList.add("btn")
    button.classList.add("btn-primary")
    button.classList.add("w-25")
    button.innerHTML = "Buy";
    button.onclick = ()=>{
        const token = window.localStorage.getItem("JWT");
        const accountId = window.localStorage.getItem("accountId");
        console.log(token);
        if(!token || !accountId) {
            alert("NOAUTH");
            return;
        }
        fetch("/api/v1/order/add", {
            method: "POST",
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer ' + token},
            body: JSON.stringify({
                count: 1,
                accountId,
                productId: product.id
            })
        }).then(async (response) => {
            console.log(response)
            if(response.status === 403) {
                alert("ERROR");
            } else {
                alert("Order was created and bills will be send on the email")
            }
        })
        fetch("/api/v1/mail/send", {
            method: "POST",
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer ' + token},
            body: JSON.stringify({
                count: 1,
                accountId,
                productId: product.id
            })
        })
    }


    cardbody.appendChild(h5);
    cardbody.appendChild(p1);
    cardbody.appendChild(p2);
    cardbody.appendChild(p3);
    cardbody.appendChild(p4);
    cardbody.appendChild(p5);
    cardbody.appendChild(button);
    card.appendChild(cardbody)
    productContainer.appendChild(card);

    return productContainer;

}
