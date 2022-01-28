(ns html-templating.core-test
  (:require [clojure.test :refer :all]
            [html-templating.core :refer :all]))

(deftest a-test
  (testing "First REPL"
    (is (= "Hello, World" (fn-1)))))
