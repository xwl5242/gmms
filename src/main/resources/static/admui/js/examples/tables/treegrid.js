(function () {
    'use strict';

    var count_root_elements = 20,
        count_deep = 5;

    for (var i = 0; i < count_root_elements; i++) {
        $("<tr></tr>").addClass("treegrid-" + i + "-0").appendTo($('.tree-table')).html("<td>根节点 " + i + "</td><td>其他信息</td>");

        for (var j = 1; j < count_deep; j++) {
            $("<tr></tr>").addClass("treegrid-" + i + "-" + j).addClass("treegrid-parent-" + i + "-" + (j - 1)).appendTo($('.tree-table')).html("<td>节点 " + i + "-" + j + "</td><td>其他信息</td>");
        }
    }

    $('.tree-table').treegrid();
})();
