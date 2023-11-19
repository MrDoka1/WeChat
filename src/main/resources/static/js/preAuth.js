const phone = document.querySelectorAll('.form-auth__input');
if (phone){
    function mask(event) {
        const keyCode = event.data;
        console.log(keyCode)

        if (keyCode != null && keyCode >= 0 && keyCode <= 9) {

            if (this.value.length === 1 || this.value.substring(0, this.value.length-1) === "+7 "
                || this.value.substring(0, this.value.length-1) === "+7" || this.value.substring(0, this.value.length-1) === "+") {
                this.value = "+7 (" + keyCode;
            }

            if (this.value.charAt(0) === "+") {
                const template = '+7 (___)___ __ __',
                    def = template.replace(/\D/g, ""),
                    val = this.value.replace(/\D/g, "");
                let i = 0,
                    newValue = template.replace(/[_\d]/g, function (a) {
                        return i < val.length ? val.charAt(i++) || def.charAt(i) : a;
                    });
                i = newValue.indexOf("_");
                if (i !== -1) {
                    newValue = newValue.slice(0, i);
                }
                let reg = template.substr(0, this.value.length).replace(/_+/g,
                    function (a) {
                        return "\\d{1," + a.length + "}";
                    }).replace(/[+()]/g, "\\$&");
                reg = new RegExp("^" + reg + "$");
                if (!reg.test(this.value) || this.value.length < 5 || keyCode > 47 && keyCode < 58) {
                    this.value = newValue;
                }
                if (event.type === "blur" && this.value.length < 5) {
                    this.value = "";
                }
            }
        } else if (this.value.charAt(0) === "+") {
            this.value = this.value.substring(0, this.value.length - 1);
        }
    }

    for (const elem of phone) {
        elem.addEventListener("input", mask);
        elem.addEventListener("focus", mask);
        elem.addEventListener("blur", function () {
            if (this.value === "+7 (" || this.value === "+7 " || this.value === "+7" || this.value === "+") {
                this.value = "";
            }
        });
    }
}

document.querySelector(".form-auth__btn").addEventListener("click", function (event) {
    const value = document.querySelector('.form-auth__input').value;
    //event.preventDefault()
})