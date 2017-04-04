var path = require('path');
var webpack = require("webpack");

module.exports = {
				
		  //add here
		  entry:{
			  demo : "./appConfig/DemoCfg.js",
			  wholestate : "./appConfig/WholeStateCfg.js",
			  wholewholestate:"./appConfig/WholeWholeStateCfg.js",
			  echart : "./appConfig/echart.js",
			  homepage : "./appConfig/HomePageCfg.js"
		  },
		  
		  
		  output: {
		    path: path.join(__dirname , "build"),
		    chunkFilename: '[name].js',
		    filename: "[name].bundle.js"
		  },
		
		module: {
	        loaders: [
	            {
	                loader: 'babel-loader',
	                test: [
	                    path.join(__dirname, 'appConfig'),
	                    path.join(__dirname, 'controller'),
	                    path.join(__dirname, 'model'),
	                    path.join(__dirname, 'services'),
	                    ],
	                query: {
	                    presets: 'es2015',
	                },
	            },

	            {
	                test: /[\/\\]node_modules[\/\\]some-module[\/\\]index\.js$/,
	                loader: "imports?this=>window"
	            },
	            {
	                // I want to uglify with mangling only app files, not thirdparty libs
	                test: /build\/mainpage\/.*\.js$/,
	                exclude: /Scripts/, // excluding .spec files
	                loader: "uglify"
	            },
	            
	        ]
	    },
		
		plugins: [
		          new webpack.NoErrorsPlugin(),
		          
		          //compress js file,do not use it until deployed 
		          // new webpack.optimize.UglifyJsPlugin({
		          //     sourceMap: false,
		          //     mangle: false,
		          //     compress: {
		          //         warnings: false
		          //     },
		          // }),
		          
		          new webpack.BannerPlugin(
		              "// gd Version 1.0  ".concat((new Date()).toLocaleString()," --By wcy ")
		              //{
		              //    raw: true, // if true, banner will not be wrapped in a comment
		              //    entryOnly: true // if true, the banner will only be added to the entry chunks
		              //}
		              ),
		          ],
		stats: {
		     colors: true
		},
		// Create Sourcemaps for the bundle
		devtool: 'source-map',
//		resolve: {
//	        modulesDirectories: ['node_modules'],
//	        alias: {
//	            'jquery': "../jquery-3.1.1.min",
//	            /*jqueryui: '../plugins/jQueryUI/jquery-ui.min',
//	            jqueryform: "../plugins/JQuery/jquery.unobtrusive-ajax.min",
//	            jqueryval: "../plugins/JQuery/jquery.validate",
//	            jqueryvalunobtrusive: "../plugins/JQuery/jquery.validate.unobtrusive.min",*/
//	            jqlayer: './unit/JqLayer/layer/layer',
//	            /*jqUeditor: '../plugins/Ueditor/ueditor.all',
//	            jqTmpl: '../plugins/jQTmpl/jquery.tmpl.min',
//	            jqPage: '../plugins/jQPage/jquery.twbsPagination',
//	            jqSelect: '../plugins/JQbootstrap-select/bootstrap-select',
//	            jqTagsinput: '../plugins/Bootstrap-tagsinput/bootstrap-tagsinput',
//	            //angular
//	            'angular': '../node_modules/angular/angular.min',
//	            'angular-ui-router': '../node_modules/angular-ui-router/release/angular-ui-router.min',
//	            'angular.animate': '../node_modules/angular-animate/angular-animate.min',
//	            'ng.ueditor': "../plugins/Ueditor/angular-ueditor",
//	            'ng.treeView': '../plugins/NgTreeView/abn_tree_directive',
//	            'ng.contextmenu': '../plugins/NgContextMenu/contextMenu'*/
//	        },
//	        module: {
//	            loaders: [
//	                { test: /jquery/, loader: 'expose?jQuery!expose?$' },//The jquery   must firstly
//	                /*{ test: /jqueryui/, loader: 'exports?jqueryui!imports?jQuery' },
//	                { test: /jqueryform/, loader: 'exports?jqueryform!imports?jQuery' },
//	                { test: /jqueryval/, loader: 'exports?jqueryval!imports?jQuery' },
//	                { test: /jqueryvalunobtrusive/, loader: 'exports?jqueryvalunobtrusive!imports?jQuery' },*/
//	                { test: /jqlayer/, loader: 'exports?jqlayer!imports?jQuery' },
//	                /*{ test: /jqUeditor/, loader: 'exports?jqUeditor!imports?jQuery' },
//	                { test: /jqTmpl/, loader: 'exports?jqTmpl!imports?jQuery' },
//	                { test: /jqPage/, loader: 'exports?jqPage!imports?jQuery' },
//	                { test: /jqSelect/, loader: 'exports?jqSelect!imports?jQuery' },
//	                { test: /jqTagsinput/, loader: 'exports?jqTagsinput!imports?jQuery' },
//	                //angular
//	                { test: /angular/, loader: 'expose?angular!expose?Angular' }, //Angular
//	                { test: /angular-ui-router/, loader: 'exports?angular-ui-router!imports?angular' },
//	                { test: /ng.ueditor/, loader: 'exports?ng.ueditor!imports?angular' },
//	                { test: /angular.animate/, loader: 'exports?angular.animate!imports?angular' },
//	                { test: /ng.treeView/, loader: 'exports?ng.treeView!imports?angular' },
//	                { test: /ng.contextmenu/, loader: 'exports?ng.treeView!imports?angular' },*/
//	            ]
//	        }
//	    },

     
};
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		