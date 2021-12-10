const productsContainer = document.querySelector("#prod_container");

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
    p2.innerHTML = product.date_from;
    const p3 = document.createElement("p");
    p3.classList.add("card-text");
    p3.innerHTML = "To";
    const p4 = document.createElement("p");
    p4.classList.add("card-text");
    p4.innerHTML = product.date_to;
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