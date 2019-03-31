var trTemplate = "<tr>\n" +
    "                    <th>\$\{name\}</th>\n" +
    "                    <th>\$\{createDate\}</th>\n" +
    "                    <th>\$\{department\}</th>\n" +
    "                    <th>\$\{role\}</th>\n" +
    "                    <th><a onclick='remove(this)'>删除</a></th>\n" +
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
    var uri = "/users"
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

function remove(ele) {
    var id = $(ele).parents("tr").children("th").first().children("input").val()
    if (!id) {
        alert("未找到有效ID")
        return
    }
    $.ajax({
        type: "DELETE",
        url: "/user/" + id,
        data: {},
        success: function (result) {
            loadTable()
            alert("已删除")
        }
    })
}

function add() {
    var datas = $("#update-form").serializeArray()
    var parameters = {}
    for (var index in datas) {
        var data = datas[index]
        if (data.value) {
            parameters[data.name] = data.value
        }
    }
    $.ajax({
        type: "POST",
        url: "/user/",
        data: parameters,
        success: function (result) {
            loadTable()
            alert("新增成功")
            $("#update-form")[0].reset()
            $("#updateModal").modal("hide")
        }
    })
}

$(function () {
    // $(".btn-search").click(loadTable)
    $(".btn-add").click(function () {
        $("#updateModal").show()
    })
    $(".btn-clear").click(function () {
        $("#search-form")[0].reset()
    })
    $("#add").click(add)
})

