var path = require('path');
var webpack = require("webpack");

module.exports = {
				
		  //add here
		  entry:{
			  demo : "./appConfig/DemoCfg.js",
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
	                    path.join(__dirname, 'services')],
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
		          ],
		stats: {
		     colors: true
		},
		// Create Sourcemaps for the bundle
		devtool: 'source-map',
		
     
};
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		