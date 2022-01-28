(ns html-templating.core-test
  (:require [clojure.test :refer :all]
            [html-templating.core :refer :all]))

(deftest a-test
  (testing "First REPL"
    (is (= "Hello, World" (fn-1)))))


(deftest create-template
  (testing "Using HTML File"
    (is (= "<html>\n  <head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n    <title>My First Template</title>\n  </head>\n  <body>\n    <h2>Hello World</h2>\n  </body>\n</html>\n"
           (fn-2-1)))))

(deftest use-list
  (testing "Using HTML File with list"
    (is (= "<ul>\n  \n  <li> 0 </li>\n  \n  <li> 1 </li>\n  \n  <li> 2 </li>\n  \n  <li> 3 </li>\n  \n  <li> 4 </li>\n  \n  <li> 5 </li>\n  \n  <li> 6 </li>\n  \n  <li> 7 </li>\n  \n  <li> 8 </li>\n  \n  <li> 9 </li>\n  \n</ul>\n"
           (fn-2-2)))))

(deftest use-map
  (testing "Using HTML File with map"
    (is (= "<p>Hello John Doe</p>" (fn-2-3)))))

(deftest use-filter
  (testing "Using Filter"
    (is (= "no files" (fn-3-1)))))

(deftest use-filter-2
  (testing "Filter is safe"
    (is (= "<DIV>I'M SAFE</DIV>" (fn-3-2)))))

(deftest define-custom-tag
  (testing "Custom tag"
    (is (= "<img src=\"http://foo.com/logo.jpg\"/>" (fn-4-1)))))

(deftest define-custom-block-tag
  (testing "Custom block tag"
    (is (= "FOO INJECTED BAZ" (fn-4-2)))))

(deftest extend-template
  (testing "Extend template"
    (is (= "<!-- -*- engine:selmer -*- -->\n<html>\n    <head>\n        <link rel=\"stylesheet\" href=\"style.css\" />\n        <title>My amazing site</title>\n    </head>\n    <body>\n        <div id=\"content\">\n            \n    default content\n    <h2>Hello World</h2>\n    <ul>\n        \n            <li>0</li>\n        \n            <li>1</li>\n        \n            <li>2</li>\n        \n            <li>3</li>\n        \n            <li>4</li>\n        \n            <li>5</li>\n        \n            <li>6</li>\n        \n            <li>7</li>\n        \n            <li>8</li>\n        \n            <li>9</li>\n        \n    </ul>\n\n        </div>\n    </body>\n</html>\n" (fn-5-1))))
  (testing "includes parent content"
    (is (clojure.string/includes?  (fn-5-1) "default content")))
  )
