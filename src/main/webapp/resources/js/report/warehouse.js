google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(function () {
    drawMonthChart();
    drawYearChart();
    drawWeekChart();
});

function drawWeekChart() {
    var options = {
        title: 'Tygodniowy raport wydatków magazynu.',
        height: 350,
        vAxis: {title: 'Ilość racji żywnościowych'},
        hAxis: {
            title: 'Dzień tygodnia',
            slantedText: true,
            slantedTextAngle: 45,
            showTextEvery: 1
        },
        seriesType: 'bars',
        series: {1: {type: 'line'}}
    };

    _drawChart("/admin/report/warehouse/week", options, 'weekChart');
}

function drawMonthChart() {
    var options = {
        title: 'Miesięczny raport wydatków magazynu.',
        height: 350,
        vAxis: {title: 'Ilość racji żywnościowych'},
        hAxis: {title: 'Tydzień roku'},
        seriesType: 'bars',
        series: {1: {type: 'line'}}
    };

    _drawChart("/admin/report/warehouse/month", options, 'monthChart');
}

function drawYearChart() {
    var options = {
        title: 'Roczny raport wydatków magazynu.',
        height: 350,
        vAxis: {title: 'Ilość racji żywnościowych'},
        hAxis: {
            title: 'Miesiąc',
            slantedText: true,
            slantedTextAngle: 45,
            showTextEvery: 1
        },
        seriesType: 'bars',
        series: {1: {type: 'line'}}
    };

    _drawChart("/admin/report/warehouse/year", options, 'yearChart');
}