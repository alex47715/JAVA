function signup() {
    const login = document.querySelector('#login').value;
    const password = document.querySelector('#password').value;
    const email = document.querySelector('#email').value;
    const resultMessage = document.querySelector('#resultMessage');

    fetch("/api/v1/auth/signup", {
        method: "POST",
        headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
        body: JSON.stringify({
            login,
            password,
            email
        })
    }).then(async (response) => {
        if(response.status === 201) {
            resultMessage.className = "successMessage";
            resultMessage.innerHTML = 'Account was created!';
        } else {
            const data = await response.json();
            resultMessage.className = "errorMessage";
            resultMessage.innerHTML = data.errors[0].message;
        }
    })
}