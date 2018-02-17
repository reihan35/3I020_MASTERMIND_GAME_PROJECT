;; # Exercice 1 : Expressions et types de base

(ns theme01-clojure.ex01-bases
  (:use midje.sweet))


;; ## Question 1 : des nombres ...

(facts
  (and

    (= (type 42) Long)

    (= (type 42.2) Double)

  	(= (+ 12 14 18) 44)

    (= (+ 12 14 (* 4 8 2)) 90)

  	(= (zero? (quot 3 2)) false)

    (= (zero? (rem 4 2)) true)

    (= (= 12.0 12) false)

    (= (== 12.0 12) true)

    (= (not= 12.0 12) true)

    (= (inc 41) 42)

    (= (dec 43) 42)

  => true)

;; ## Question 2 : des booléens ... et nil

(facts
  (and

    (= (type true) Boolean)

    (= (if false "un" "deux") "deux")

    (= (if true "un" "deux") "un")

    (= (if 42 "un" "deux") "un")

    (= (if nil "un" "deux") "deux")

    (= (nil? nil) true)

    (= (nil? false) false)

    (= (when 42 "ouaye") "ouaye")

    (= (when (not 42) "ouaye"))

    (= (when-not (not 42) "ouaye") "ouaye")

  => true)

;; ## Question 3 : symboles et keywords

(facts
  (and

    (= (symbol? 'blabla) true)

    (= (keyword? :blabla) true)

    (= (symbol? :blabla) false)

    (= (name 'blabla) "blabla")

    (= (name :blabla) "blabla")

  => true)

;; ## Question 4 : chaînes de caractères

(facts
  (and

    (= (type "Hello le monde") String)

    (= (count "Hello le monde") 14)

    (= (str (* 2 21) " = " '(2 * 21)) "42 = (2 * 21)")

    (= (get "Hello le monde" 9) \m) ;; Hypothès : si ce n'est pas un string, il faut ajouter le symbole \

    (= (get "Hello" 9) nil)

    (= (subs "Hello le monde" 6) "le monde")

    (= (subs "Hello le monde" 3 7) "lo l")

    (= (clojure.string/replace "Hello le monde" "e" "u")"Hullo lu mondu")


    (= (clojure.string/replace "Hello le monde" #"[aeiou]" "x")"Hxllx lx mxndx")

    (= (clojure.string/replace "Hello le monde" #"[^aeiou]" "x")"xexxoxxexxoxxe")

  => true)


