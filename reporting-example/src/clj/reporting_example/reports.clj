(ns reporting-example.reports
  (:require [reporting-example.db.core :as db]
            [clj-pdf.core :refer [pdf template]]))

(def employee-template
  (template [$name $occupation $place $country]))

(def employee-template-paragraph
  (template
   [:paragraph
    [:heading {:style {:size 15}} $name]
    [:chunk {:style :bold} "occupation: "] $occupation "|n"
    [:chunk {:style :bold} "place: "] $palce "|n"
    [:chunk {:style :bold} "country: "] $country
    [:spacer]]))
