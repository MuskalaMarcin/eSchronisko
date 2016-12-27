google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(function () {
    drawMonthChart();
    drawYearChart();
    drawWeekChart();
});

function drawWeekChart() {
    var options = {
        title: 'Tygodniowy raport stanu magazynu.',
        vAxis: {title: 'Ilość racji żywnościowych'},
        hAxis: {title: 'Dzień tygodnia'},
        seriesType: 'bars',
        series: {2: {type: 'line'}}
    };

    _drawChart("http://localhost:8080/admin/report/warehouse/week", options, 'weekChart');
}

function drawMonthChart() {
    var options = {
        title: 'Miesięczny raport stanu magazynu.',
        vAxis: {title: 'Ilość racji żywnościowych'},
        hAxis: {title: 'Tydzień roku'},
        seriesType: 'bars',
        series: {2: {type: 'line'}}
    };

    _drawChart("http://localhost:8080/admin/report/warehouse/month", options, 'monthChart');
}

function drawYearChart() {
    var options = {
        title: 'Roczny raport stanu magazynu.',
        vAxis: {title: 'Ilość racji żywnościowych'},
        hAxis: {title: 'Miesiąc'},
        seriesType: 'bars',
        series: {2: {type: 'line'}}
    };

    _drawChart("http://localhost:8080/admin/report/warehouse/year", options, 'yearChart');
}