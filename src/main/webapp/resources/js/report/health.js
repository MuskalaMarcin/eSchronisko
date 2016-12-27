google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(function () {
    drawMonthChart();
    drawYearChart();
    drawWeekChart();
});

function drawWeekChart() {
    var options = {
        title: 'Tygodniowy raport zdrowia.',
        height: 350,
        vAxis: {title: 'Ilość zabiegów'},
        hAxis: {
            title: 'Dzień tygodnia',
            slantedText: true,
            slantedTextAngle: 45,
            showTextEvery: 1
        },
        seriesType: 'bars',
        series: {1: {type: 'line'}}
    };

    _drawChart("http://localhost:8080/admin/report/health/week", options, 'weekChart');
}

function drawMonthChart() {
    var options = {
        title: 'Miesięczny raport zdrowia.',
        height: 350,
        vAxis: {title: 'Ilość zabiegów'},
        hAxis: {title: 'Tydzień roku'},
        seriesType: 'bars',
        series: {1: {type: 'line'}}
    };

    _drawChart("http://localhost:8080/admin/report/health/month", options, 'monthChart');
}

function drawYearChart() {
    var options = {
        title: 'Roczny raport zdrowia.',
        height: 350,
        vAxis: {title: 'Ilość zabiegów'},
        hAxis: {
            title: 'Miesiąc',
            slantedText: true,
            slantedTextAngle: 45,
            showTextEvery: 1
        },
        seriesType: 'bars',
        series: {1: {type: 'line'}}
    };

    _drawChart("http://localhost:8080/admin/report/health/year", options, 'yearChart');
}