(ns brew-for-the-brave-and-true.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [brew-for-the-brave-and-true.core :refer [validate if-valid when-valid]]))

(def order-details-validations
  {:name
   ["Please enter a name." not-empty]

   :email
   ["Please enter an email address." not-empty

    "Your email address doesn't look like an email address."
    #(or (empty? %) (re-seq #"@" %))]})

(defn v
  [order-details]
  (validate order-details order-details-validations))

(def errors "test")

(deftest test-validate
  (testing "invalid-email"
    (is (= (v {:name "Mitchard Blimmons" :email "mitchard.blimmonsgmail.com"})
           {:email ["Your email address doesn't look like an email address."]})))

  (testing "no-email"
    (is (= (v {:name "Mitchard Blimmons" :email ""})
           {:email ["Please enter an email address."]})))

  (testing "no-name"
    (is (= (v {:name "" :email "mitchard.blimmons@gmail.com"})
           {:name ["Please enter a name."]})))

  (testing "no-email-and-no-name"
    (is (= (v {:name "" :email ""})
           {:name ["Please enter a name."] :email ["Please enter an email address."]})))

  (testing "if-valid-invalid-email"
    (is (= (if-valid {:name "Mitchard Blimmons" :email "mitchard.blimmonsgmail.com"}
                     order-details-validations errors
                     :success
                     errors)
           {:email ["Your email address doesn't look like an email address."]})))

  (testing "when-valid-invalid-email"
    (is (= (when-valid {:name "Mitchard Blimmons" :email "mitchard.blimmonsgmail.com"}
                       order-details-validations errors :success)
           nil)))

  (testing "when-valid-all-valid"
    (is (= (when-valid {:name "Mitchard Blimmons" :email "mitchard.blimmons@gmail.com"}
                       order-details-validations errors :success)
           :success))))
