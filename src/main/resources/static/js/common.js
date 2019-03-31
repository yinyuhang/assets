(function ($, window, document, undefined) {
    var Pagination = function (element, args) {
        this.$element = element;
        this.args = args;
    }

    Pagination.prototype.construtor = Pagination;

    Pagination.prototype = {
        init: function () {
            var that = this;
            return (function () {
                that.renderHtml(that.args);
                that.bindEvents(that.args);
            })()
        },

        renderHtml: function (args) {
            var that = this,
                currPage = args.currPage,
                totalCount = args.totalCount,
                leastCount = 4,
                str = '';

            str += '<li class="pagination-item"><a href="javascript:void(0)" data-page="prev">Previous</li>'

            // 页码大于等于leastCount时，添加 页码1
            if (currPage != 1 && currPage >= leastCount && totalCount != leastCount) {
                str += '<li class="pagination-item"><a href="javascript:void(0)" data-page="1">1</a></li>';
            }

            // 页码大于leastCount，小于总页码，并且总页码大于leastCount+1
            if (currPage > leastCount && currPage <= totalCount && totalCount > leastCount + 1) {
                str += '<li class="pagination-item"><a href="javascript:void(0)" data-page="...">...</a></li>';
            }

            var start = currPage - 2,
                end = currPage + 2;

            if ((start > 1 && currPage < leastCount) || currPage == 1) {
                end++
            }

            if (currPage > (totalCount - leastCount) && currPage >= totalCount) {
                start--;
            }

            for (; start <= end; start++) {
                if (start <= totalCount && start >= 1) {
                    str += '<li class="pagination-item"><a href="javascript:void(0)" class="' + (start == currPage ? "current" : "") + '" data-page="' + start + '">' + start + '</a></li>';
                }
            }

            // currPage + 2 小于 totalCount - 1 并且 当前页面不是第一页，总页码要大于 leastCount + 1
            if ((currPage + 2 < totalCount - 1) && currPage >= 1 && totalCount > leastCount + 1) {
                str += '<li class="pagination-item"><a href="javascript:void(0)" data-page="...">...</a></li>';
            }

            if (currPage != totalCount && currPage < totalCount - 2 && totalCount != leastCount) {
                str += '<li class="pagination-item"><a href="javascript:void(0)" data-page="' + totalCount + '">' + totalCount + '</a></li>';
            }

            str += '<li class="pagination-item"><a href="javascript:void(0)" data-page="next">Next</li>'

            that.$element.html(str);
        },

        bindEvents: function (args) {
            var that = this;
            that.$element.on('click', 'a', function (ev) {
                var args = {}
                args.currPage = $("#page-index").text()
                args.totalCount = $("#total-page-num").text()
                var current = $(this).data('page');
                if (current === 'prev') {
                    var old = $('.pagination-item  a.current').data('page');
                    if (old <= 1) return
                    else {
                        old--;
                        args.currPage = old;
                        that.renderHtml(args);
                    }
                } else if (current === 'next') {
                    var old = $('.pagination-item  a.current').data('page');
                    if (old >= args.totalCount) return
                    else {
                        old++;
                        args.currPage = old;
                        that.renderHtml(args);
                    }
                } else if (Number.isNaN(Number(current)) || !current) {
                    return
                } else {
                    args.currPage = current;
                    that.renderHtml(args);
                }
                if (toString.call(args.cb) == '[object Function]') {
                    args.cb(args.currPage)
                }
            });
        }
    }
    $.fn.extend({
        pagination: function (options) {
            return new Pagination(this, options).init();
        }
    });

})(jQuery, window, document)

$(function () {
    loadTable()

    $("input").each(function (index, node) {
        if ($(node).hasClass("date")) {
            $(node).datetimepicker({
                language:  'zh-CN',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
            })
        }
    })

    $(".btn-search").click(function () {
        loadTable()
    })
})

function formatDate (data, attr) {
    if (data[attr]) {
        data[attr] = data[attr].substr(0, 10)
    }
}