var trTemplate = "<tr>\n" +
    "                    <th>${id}</th>\n" +
    "                    <th>${createDate}</th>\n" +
    "                    <th>${createUser}</th>\n" +
    "                    <th>${ip}</th>\n" +
    "                </tr>"


function fill(data) {
    if (!data) {
        return ""
    }
    var tr = trTemplate
    var id = "<input class='hide' value='" + data.id + "' readonly/>" + data.id.substr(0, 6);
    tr = tr.replace("${id}", id)
    for (var attr in data) {
        if (!data[attr]) {
            data[attr] = ""
        }
        tr = tr.replace("${" + attr + "}", data[attr])
    }
    return tr
}

function loadTable(goNext) {
    var uri = "/logs"
    var conditions = $("#search-form").serializeArray()
    var parameters = {}
    for (var index in conditions) {
        var condition = conditions[index]
        if (condition.value) {
            parameters[condition.name] = condition.value
        }
    }
    var pageIndex = $("#page-index").text();
    if (!pageIndex || !goNext) {
        pageIndex = 0
    }
    parameters["pageIndex"] = pageIndex
    parameters["pageSize"] = 10
    $.get(uri, parameters, function (result) {
        console.log("got form data" + result)
        var tBody = $("#data-table");
        tBody.empty()
        if (result.content) {
            for (var index in result.content) {
                var data = result.content[index]
                if (data["createUser"])
                    data["createUser"] = data["createUser"]["name"]
                if (data["modifyUser"])
                    data["modifyUser"] = data["modifyUser"]["name"]
                if (data["createDate"])
                    data["createDate"] = data["createDate"].substr(0, 10)
                if (data["buyDate"])
                    data["buyDate"] = data["buyDate"].substr(0, 10)
                tBody.append(fill(data))
            }
            var pageIndex = result['number'];
            var totalPages = result['totalPages'];
            pageIndex++;
            $("#page-index").text(pageIndex)
            $("#total-page-num").text(totalPages)
            $('.customPagination').pagination({
                totalCount: totalPages,
                currPage: pageIndex,
                cb: function (current) {
                    console.log('回调执行了', current)
                }
            })
        }
    })
}

$(function () {
    $(".btn-clear").click(function () {
        $("#search-form")[0].reset()
    })
})

