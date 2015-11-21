// Karma configuration
// Generated on Tue Feb 18 2014 13:08:43 GMT-0600 (CST)

module.exports = function (karma) {
  karma.set({

    // base path, that will be used to resolve files and exclude
    basePath: './../public',


    // frameworks to use
    frameworks: ['jasmine'],

    files: [

    /******* Core Dependencies (Order Matters!) ******************/
        './../../main/resources/public/lib/angular/angular.js',
        './../../main/resources/public/lib/angular-route/angular-route.js',
        './../../main/resources/public/lib/angular-ui-router/release/angular-ui-router.min.js',
        './../../main/resources/public/lib/angular-animate/angular-animate.js',
        './../../main/resources/public/lib/angular-sanitize/angular-sanitize.js',
        './../../main/resources/public/lib/angular-ui-select/dist/select.js',
        './../../main/resources/public/lib/angular-loading-bar/build/loading-bar.js',
        './../../main/resources/public/app/**/*.js',

    /******* Test Libraries (relative to basePath) ******************/
        './../../main/resources/public/lib/angular-mocks/angular-mocks.js',

    /******* Test Fixtures ******************/
      {
        pattern: './app/fixtures/*.html',
        watched: false,
        served: true,
        included: false
      },

    /******* Jasmine Tests ******************/
      './app/**/*-test.js'

    ],

    preprocessors: {
      // define which files to include in coverage reports
      './../../main/resources/public/app/**/*.js': ['coverage']
    },

    coverageReporter: {
      type : 'html',
      dir : 'coverage/'
    },

    // list of files to exclude
    exclude: [
    ],

    // use dots reporter, as travis terminal does not support escaping sequences
    // possible values: 'dots', 'progress'
    // CLI --reporters progress
    reporters: ['progress', 'coverage'],

    // web server port
    // CLI --port 9876
    port: 9876,

    // cli runner port
    runnerPort: 9100,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: karma.LOG_DISABLE || karma.LOG_ERROR || karma.LOG_WARN || karma.LOG_INFO || karma.LOG_DEBUG
    logLevel: karma.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: false,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: ['Chrome', 'PhantomJS'],

    // If browser does not capture in given timeout [ms], kill it
    captureTimeout: 60000,

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: true
  });
};
