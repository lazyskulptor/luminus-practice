(ns guestbook.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]))

(dom/render
 [:div
  [:div {:id "hello", :class "content"} [:h1 "Hello, Auto!"]]
  [:div#hello.content>h1 "Hello, Reagent"]]
 (.getElementById js/document "content"))
