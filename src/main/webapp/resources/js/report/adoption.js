google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(function () {
    drawMonthChart();
    drawYearChart();
    drawWeekChart();
});

function drawWeekChart() {
    var responseData = JSON.parse($.ajax({
        url: "http://localhost:8080/admin/report/adoption/week",
        dataType: "json",
        async: false
    }).responseText);

    var data = google.visualization.arrayToDataTable(responseData);

    var options = {
        title: 'Tygodniowy raport adopcji.',
        vAxis: {title: 'Ilość'},
        hAxis: {title: 'Dzień tygodnia'},
        seriesType: 'bars',
        series: {2: {type: 'line'}}
    };

    var chart = new google.visualization.ComboChart(document.getElementById('weekChart'));
    chart.draw(data, options);
}

function drawMonthChart() {
    var responseData = JSON.parse($.ajax({
        url: "http://localhost:8080/admin/report/adoption/month",
        dataType: "json",
        async: false
    }).responseText);

    var data = google.visualization.arrayToDataTable(responseData);

    var options = {
        title: 'Monthly Coffee Production by Country',
        vAxis: {title: 'Cups'},
        hAxis: {title: 'Month'},
        seriesType: 'bars',
        series: {5: {type: 'line'}}
    };

    var chart = new google.visualization.ComboChart(document.getElementById('monthChart'));
    chart.draw(data, options);
}

function drawYearChart() {
    var responseData = JSON.parse($.ajax({
        url: "http://localhost:8080/admin/report/adoption/year",
        dataType: "json",
        async: false
    }).responseText);

    var data = google.visualization.arrayToDataTable(responseData);

    var options = {
        title: 'Monthly Coffee Production by Country',
        vAxis: {title: 'Cups'},
        hAxis: {title: 'Month'},
        seriesType: 'bars',
        series: {5: {type: 'line'}}
    };

    var chart = new google.visualization.ComboChart(document.getElementById('yearChart'));
    chart.draw(data, options);
}