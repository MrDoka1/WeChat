document.querySelector(".form-auth__btn").addEventListener("click", function(event) {
    const password = document.querySelector(".password");
    const copypassword = document.querySelector(".copy-password");
    const firstname = document.querySelector(".firstname");
    const lastname = document.querySelector(".lastname");
    const birthdate = document.querySelector(".birthdate");
    let checkAll = true;
    if((password.value !== copypassword.value) || password.value === "") {
        password.classList.add("--active");
        copypassword.classList.add("--active");
        checkAll = false;
    } else {
        password.classList.remove("--active");
        copypassword.classList.remove("--active");
    }

    if (firstname.value === "") {
        firstname.classList.add("--active");
        checkAll = false;
    } else {
        firstname.classList.remove("--active");
    }

    if (lastname.value === "") {
        lastname.classList.add("--active");
        checkAll = false;
    } else {
        lastname.classList.remove("--active");
    }

    if (new Date(birthdate.value) > new Date() || birthdate.value === "") {
        birthdate.classList.add("--active");
        checkAll = false;
    } else {
        birthdate.classList.remove("--active");
    }

    if (checkAll) {
        // Преобразования перед отправкой формы
        document.querySelector(".email").removeAttribute("disabled");
        document.querySelector(".email").value = email;
    } else {
        event.preventDefault()
    }

    
}, false);