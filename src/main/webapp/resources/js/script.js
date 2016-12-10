$(document).ready(function () {
    _setLogo()
    $('.carousel').carousel({
        interval: 2000
    })
})

function _setLogo() {
    var logoBackgrounds = ['logoDog.jpg', 'logoCat.jpg']
    var randomBackground = Math.floor(Math.random() * logoBackgrounds.length);
    $('#logo').css('background-image', 'url(../../resources/images/logo/' + logoBackgrounds[randomBackground] + ')');
}

function scrollPageToTop() {
    $("html, body").animate({scrollTop: 0}, 300);
}
