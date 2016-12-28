/**
 * Created by Marcin on 12.12.2016.
 */
$(document).ready(function () {
    var searchInput = $("#searchPrefix");
    var table = $(".responseTable");

    searchInput.keyup(function () {
        var suggestionsDiv = $("#suggestionsDiv");
        var responseBody = $("#responseBody");

        if ($(this).val()) {
            $.post('/admin/users/search', {prefix: searchInput.val()}, function (returnedData) {
                suggestionsDiv.empty();
                responseBody.empty();
                table.hide();
                if (returnedData.length > 0) {
                    $.each(returnedData, function (k, v) {
                        var activateButton;
                        if (v.isActive) {
                            activateButton = "<td class=\"buttonCell\"><a href=\"/admin/deactivateuser/" + v.username + "\"><button class=\"btn btn-warning\">Dezaktywuj</button></a></td>"
                        } else {
                            activateButton = "<td class=\"buttonCell\"><a href=\"/admin/activateuser/" + v.username + "\"><button class=\"btn btn-success\">Aktywuj</button></a></td>"
                        }
                        var resultRow = "<tr><td>" + v.name + "</td><td>" + v.surname + "</td><td>" + v.username + "</td>"
                            + activateButton + "<td class=\"buttonCell\"><a href=\"/admin/edituser/" + v.username + "\">"
                            + "<a href=\"/admin/edituser/" + v.username + "\"><button class = \"btn btn-info\">Edytuj</button>"
                            + "</a></td><td class=\"buttonCell\"><a href=\"/admin/deleteuser/" + v.username + "\">"
                            + "<button class=\"btn btn-danger\">Usuń</button></a></td></tr>";
                        responseBody.append(resultRow);
                        table.show();
                    })
                }
                else {
                    suggestionsDiv.append("Brak wyników.");
                    responseBody.empty();
                    table.hide();
                }
            });
        }
        else {
            responseBody.empty();
            suggestionsDiv.empty();
            table.hide();
        }
    });
});