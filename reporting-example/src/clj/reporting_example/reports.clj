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

(defn table-report [out]
  (let [data (db/read-employee)]
    (pdf
     [{:header "Employee List"}
      (when-not (empty? data)
        (into [:table
               {:border false
                :cell-border false
                :header [{:backdrop-color [0 150 150]}
                         "Name" "Occupation" "Place" "Country"]}]
              (employee-template (db/read-employee))))]
     out)))

(defn list-report [out]
  (pdf
   [{}
    [:heading {:size 10} "Employees"]
    [:line]
    [:spacer]
    (employee-template-paragraph (db/read-employee))]
   out))
