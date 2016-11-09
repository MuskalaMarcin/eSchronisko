$(document).ready(function () {
    _setLogo();
})

function _setLogo() {
    var logoBackgrounds = ['logoDog.jpg', 'logoCat.jpg']
    var randomBackground = Math.floor(Math.random() * logoBackgrounds.length);
    console.log(logoBackgrounds[randomBackground]);
    $('.logo').css('background-image', 'url(../../resources/images/logo/' + logoBackgrounds[randomBackground] + ')');
}

function scrollPageToTop() {
    $("html, body").animate({scrollTop: 0}, 300);
}