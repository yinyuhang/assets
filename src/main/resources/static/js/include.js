var includeFiles = []
// import vue.js
includeFiles.push({
    "type": "script",
    "attrs": {"src": "/framework/vue.js"},
})
// import bootstrap.css
includeFiles.push({
    "type": "link",
    "attrs": {"href": "/framework/bootstrap/bootstrap.css", "rel": "stylesheet", "type": "text/css"},
})
// import jquery.js
includeFiles.push({
    "type": "script",
    "attrs": {"src": "/framework/bootstrap/jquery.js"},
})
// import popper.js
includeFiles.push({
    "type": "script",
    "attrs": {"src": "/framework/bootstrap/popper.js"},
})
// import bootstrap.js
includeFiles.push({
    "type": "script",
    "attrs": {"src": "/framework/bootstrap/bootstrap.js"},
})
// import common.css
includeFiles.push({
    "type": "link",
    "attrs": {"href": "/css/common.css", "rel": "stylesheet", "type": "text/css"},
})
includeFiles.push({
    "type": "script",
    "attrs": {"src": "/js/common.js"},
})
function importFile(position) {
    var ele
    if (position)
        ele = document.getElementById(position)
    else
        ele = document.getElementsByTagName("head")[0]

    var files = includeFiles
    files.forEach(function (value) {
        var node = document.createElement(value.type)
        for (var attrName in value.attrs)
            node.setAttribute(attrName, value.attrs[attrName])
        ele.appendChild(node)
    })
}

window.onload=function () {
    importFile()
}
