(ns html-templating.core
  (:require
   [selmer.parser :as selmer]
   [selmer.filters :as filters]))

(filters/add-filter! :empty? empty?)
(filters/add-filter! :foo
                     (fn [x] [:safe (.toUpperCase x)]))

(defn fn-1 []
  (selmer/render "Hello, {{name}}" {:name "World"}))

(defn fn-2-1 []
  (selmer/render-file "hello.html" {:name "World"}))

(defn fn-2-2 []
  (selmer/render-file "hello-2.html" {:items (range 10)}))

(defn fn-2-3 []
  (selmer/render "<p>Hello {{user.first}} {{user.last}}</p>"
                 {:user {:first "John" :last "Doe"}}))

(defn fn-3-1 []
  (selmer/render "{% if files|empty? %}no files{% else %}files{% endif %}" {:files []}))

(defn fn-3-2 []
  (selmer/render "{{x|foo}}" {:x "<div>I'm safe</div>"}))

(selmer/add-tag! :image
                 (fn [args context-map]
                   (str "<img src=" (first args) "/>")))

(defn fn-4-1 []
  (selmer/render "{% image \"http://foo.com/logo.jpg\" %}" {}))

(selmer/add-tag! :uppercase
                 (fn [args context-map block]
                   (.toUpperCase (get-in block [:uppercase :content])))
                 :enduppercase)

(defn fn-4-2 []
  (selmer/render "{% uppercase %}foo {{bar}} baz{% enduppercase %}"
                 {:bar "injected"}))

(defn fn-5-1 []
  (selmer/render-file "hello-3.html" {:name "World" :items (range 10)}))

(defn fn-6-1 []
  (selmer/render-file "hello-4.html" {:name "World" :items (range 10)}))
