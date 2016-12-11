/**
 * Created by Marcin on 11.12.2016.
 */
$("#password").change(validateRegisterForm);
$("#passwordRepeat").keyup(validateRegisterForm);

function validateRegisterForm() {
    var passwordInput = $("#password");
    var passwordRepeatInput = $("#passwordRepeat");
    if (passwordInput.val() != passwordRepeatInput.val()) {
        passwordRepeatInput.get(0).setCustomValidity("Hasła nie zgadzają się ze sobą");
    }
    else {
        passwordRepeatInput.get(0).setCustomValidity("");
    }
}