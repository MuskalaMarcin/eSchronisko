/**
 * Created by Marcin on 12.12.2016.
 */
$(document).ready(function () {
    var searchInput = $("#searchPrefix");

    searchInput.keyup(function () {
        var suggestionsDiv = $("#suggestionsDiv");

        if ($(this).val()) {
            $.post('http://localhost:8080/admin/users/search', {prefix: searchInput.val()}, function (returnedData) {
                suggestionsDiv.empty();
                if (returnedData.length > 0) {
                    $.each(returnedData, function (k, v) {
                        var list = $("<ul/>").attr("id", "suggestionsList").appendTo(suggestionsDiv);
                        var listEl = "<table class='min-table'><tr><td class='min-cell'>" + v.name + "</td><td class='min-cell'>" + v.surname + "</td><td class='min-cell'>" + v.username + "</td></tr></table>";
                        $("<li/>").attr("class", "suggestion").append(listEl).click(function () {
                            window.location.href = 'http://localhost:8080/admin/showuser/' + v.username;
                        }).appendTo(list);
                    })
                }
                else {
                    suggestionsDiv.append("Brak sugestii.");
                }
            });
        }
        else{
            suggestionsDiv.empty();
        }
    });
});