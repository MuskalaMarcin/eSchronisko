google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(function () {
    drawMonthChart();
    drawYearChart();
    drawWeekChart();
});

function drawWeekChart() {
    var options = {
        title: 'Tygodniowy raport adopcji.',
        height: 350,
        vAxis: {title: 'Ilość'},
        hAxis: {
            title: 'Dzień tygodnia',
            slantedText: true,
            slantedTextAngle: 45,
            showTextEvery: 1
        },
        seriesType: 'bars',
        series: {2: {type: 'line'}}
    };

    _drawChart("/admin/report/adoption/week", options, 'weekChart');
}

function drawMonthChart() {
    var options = {
        title: 'Miesięczny raport adopcji.',
        height: 350,
        vAxis: {title: 'Ilość'},
        hAxis: {
            title: 'Tydzień roku',
        },
        seriesType: 'bars',
        series: {2: {type: 'line'}}
    };

    _drawChart("/admin/report/adoption/month", options, 'monthChart');
}

function drawYearChart() {
    var options = {
        title: 'Roczny raport adopcji.',
        height: 350,
        vAxis: {title: 'Ilość'},
        hAxis: {
            title: 'Miesiąc',
            slantedText: true,
            slantedTextAngle: 45,
            showTextEvery: 1
        },
        seriesType: 'bars',
        series: {2: {type: 'line'}}
    };

    _drawChart("/admin/report/adoption/year", options, 'yearChart');
}