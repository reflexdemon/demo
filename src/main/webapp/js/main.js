//var remoteServer = "http://demo-venkatvp.rhcloud.com/";
var remoteServer = "./";
//var remoteServer = "http://localhost:8080/demo/";
window.HomeView = Backbone.View.extend({

    template:_.template($('#home').html()),

    render:function (eventName) {
        $(this.el).html(this.template());
        return this;
    }
});

window.Page1View = Backbone.View.extend({

    template:_.template($('#page1').html()),

    render:function (eventName) {
        $(this.el).html(this.template());
        return this;
    }
});

window.Page2View = Backbone.View.extend({

    template:_.template($('#page2').html()),

    render:function (eventName) {
        $(this.el).html(this.template());
        return this;
    }
});


window.Result1View = Backbone.View.extend({

    template:_.template($('#result1').html()),

    render:function (eventName) {
        $(this.el).html(this.template());
        var fahernheitValue = parseFloat($("#fahrenheitTxt").val());
		var urlStr = remoteServer + "services/f2c/" + fahernheitValue;
		$.ajax({
		  type: "GET",
		  url: urlStr,
		  dataType: "json"
		}).done(function(data) {
                        console.log("JSON Response:" + data);
                        console.log("Celsius:" + data.celsius);
			$("#output1").html(data.celsius);
		});
        return this;
    }
});

window.Result2View = Backbone.View.extend({

    template:_.template($('#result2').html()),

    render:function (eventName) {
        $(this.el).html(this.template());
        var celsiusValue = parseFloat($("#celsiusTxt").val());
		var urlStr = remoteServer + "services/c2f/" + celsiusValue;
		$.ajax({
		  type: "GET",
		  url: urlStr,
		  dataType: "json"
		}).done(function(data) {
			console.log("JSON Response:" + data);
                        console.log("Fahernheit:" + data.fahrenheit);
			$("#output2").html(data.fahernheit);
		});
        return this;
    }
});

var AppRouter = Backbone.Router.extend({

    routes:{
        "":"home",
        "page1":"page1",
        "page2":"page2",
        "result1":"result1",
        "result2":"result2"
    },

    initialize:function () {
        // Handle back button throughout the application
        $('.back').live('click', function(event) {
            window.history.back();
            return false;
        });
        this.firstPage = true;
    },

    home:function () {
        console.log('#home');
        this.changePage(new HomeView());
    },

    page1:function () {
        console.log('#page1');
        this.changePage(new Page1View());
    },

    page2:function () {
        console.log('#page2');
        this.changePage(new Page2View());
    },
            
    result1:function () {
        console.log('#result1');
        this.changePage(new Result1View());
    },
            
    result2:function () {
        console.log('#result2');
        this.changePage(new Result2View());
    },

    changePage:function (page) {
        $(page.el).attr('data-role', 'page');
        page.render();
        $('body').append($(page.el));
        var transition = $.mobile.defaultPageTransition;
        // We don't want to slide the first page
        if (this.firstPage) {
            transition = 'none';
            this.firstPage = false;
        }
        $.mobile.changePage($(page.el), {changeHash:false, transition: transition});
    }

});

$(document).ready(function () {
    console.log('document ready');
    app = new AppRouter();
    Backbone.history.start();
});