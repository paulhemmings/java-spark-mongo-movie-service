module.exports = function(grunt) {

    // 1. All configuration goes here

    grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),

        project: {
            root: './src/main/resources',
            app: '<%= project.root %>/public/app',
            scss: [
                '<%= project.root %>/scss/*.scss'
            ]
        },

        karma: {
            unit: {
                configFile: './src/test/public/karma.client.config.js',
                singleRun: true,
                plugins:[
                    'karma-jasmine',
                    'karma-coverage',
                    'karma-chrome-launcher',
                    'karma-phantomjs-launcher'
                ]
            }
        },

        sass : {
            dev: {
                options: {
                    style: 'expanded',
                    compass: false
                },
                files: {
                    '<%= project.root %>/public/styles/movies.css': '<%= project.scss %>'
                }
            },
            dist: {
                options: {
                    style: 'compressed',
                    compass: true
                },
                files: {
                    '<%= project.root %>/public/styles/movies.css': '<%= project.scss %>'
                }
            }
        },

        watch: {
            sass: {
                files: '<%= project.root %>/scss/{,*/}*.{scss,sass}',
                tasks: ['sass:dev']
            }
        },

        concat: {
            dist: {
                src: [
                    '<%= project.app %>/**/*.js'   // All the app scripts
                ],
                dest: '<%= project.root %>/build/public/production.js'
            }
        },

        uglify: {
            build: {
                src: '<%= project.root %>/public/build/production.js',
                dest: '<%= project.root %>/public/production.min.js'
            }
        },

        jshint: {
          options: {
            jshintrc: '.jshintrc',
            reporter: require('jshint-stylish')
          },
          all: {
            src: [
              '<%= project.app %>/{,*/}*.js'
            ]
          }
        },

        run: {
            install: {
                cmd: 'npm',
                args: [
                    'install'
                ]
            },
            start: {
                cmd: 'npm',
                args: [
                    'start'
                ]
            }
        }

    });

    // 3. Where we tell Grunt which plugins we intend to use

    grunt.loadNpmTasks('grunt-run');
    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-jshint');

    // 4. Where we tell Grunt what to do when we type "grunt" into the terminal.

    grunt.registerTask('default', ['run:install', 'sass:dev', 'run:start' ]);

    // 5. Where we tell Grunt what other tasks to run

    grunt.registerTask('test', ['run:install', 'karma', 'jshint']);
    grunt.registerTask('build', ['run:install', 'sass:dev']);
    grunt.registerTask('pre-deploy', ['sass:dist', 'concat', 'uglify']);

};
