'use strict';

var src = {public: 'public/'};

var gulp = require('gulp'),
    webserver = require('gulp-webserver'),
    sass = require('gulp-sass'),
    concat = require('gulp-concat'),
    minifyCSS = require('gulp-minify-css'),
    concatCss = require('gulp-concat-css'),
    wiredep = require('wiredep').stream,
    autoprefixer = require('gulp-autoprefixer');

gulp.task('sass', function () {
    return gulp.src(src.public + 'sass/**/*.sass')
        .pipe(sass().on('error', sass.logError))
        .pipe(minifyCSS())
        .pipe(concat('main.min.css'))
        .pipe(autoprefixer())
        .pipe(gulp.dest(src.public + 'dist/css'));
});

gulp.task('js', function () {
    return gulp.src(src.public + 'app/**/**/*.js')
        .pipe(concat('main.js'))
        .pipe(gulp.dest(src.public + 'dist/js/'));
});

gulp.task('default', function () {
    gulp.watch(src.public + 'sass/**/*.sass', ['sass']);
    gulp.watch(src.public + 'app/**/**/*.js', ['js']);
    gulp.watch('bower.json', ['bower']);
});

gulp.task('bower', function () {
    gulp.src(src.public + 'index.html')
        .pipe(wiredep({
            directory: src.public + 'bower_components'
        }))
        .pipe(gulp.dest(src.public));
});
