;; # Exercice 2 : Factorielle, Fib et récursion terminale

(ns theme01-clojure.ex02-fact-fib
  (:use midje.sweet))



;; ## Question 1

(declare factorial-rec)

(defn factorial-rec [n]
  (if (<= n 1)
    1
    (* n (factorial-rec(- n 1)))))

(fact "`factorial-rec` calcule bien la factorielle."
      (factorial-rec 0) => 1
      (factorial-rec 1) => 1
      (factorial-rec 5) => 120
      (factorial-rec 6) => 720)

(fact "`factorial-rec` consomme beaucoup (trop) de pile."
      (try
        (factorial-rec 9000N)
        :ok-pas-de-probleme  ;; on arrive ici sans problème ?
        (catch java.lang.StackOverflowError e :plus-de-pile))
      => :plus-de-pile)


;; ## Question 2

(declare factorial)

(defn factorial [n]
  (if (<= n 1)
    1
    (loop [sum 1, m 1]
      (if (= m (+ n 1))
        sum
        (recur (* sum m) (inc m))))))




(fact "`factorial` calcule bien la factorielle."
      (factorial 0) => 1
      (factorial 1) => 1
      (factorial 5) => 120
      (factorial 6) => 720)

(fact "`factorial` ne consomme pas (trop) de pile."
      (try
        (factorial 9000N)
        :ok-pas-de-probleme  ;; on arrive ici sans problème ?
        (catch java.lang.StackOverflowError _ :plus-de-pile))
      => :ok-pas-de-probleme)

;; ## Question 3

(declare fibo-rec)


(fact "Les premiers termes de `fibo-rec` sont justes,
      (d'après Wikipedia."
      (for [k (range 11)]
        (fibo-rec k)) => '(0 1 1 2 3 5 8 13 21 34 55))

(fact "`fibo-rec` consomme énormément de pile."
      (try (fibo-rec 100000N)
        (catch java.lang.StackOverflowError _ :plus-de-pile))
        => :plus-de-pile)

(fact "`fibo-rec` est super lente."
      (>= (Integer/parseInt
           (second
            (re-find #"(\d+)\." (with-out-str (time (fibo-rec 35))))))
          1000)
      => true)

;; ## Question 4

(declare fibo)


(fact "Les premiers termes de `fibo` sont justes,
      (d'après Wikipedia."
      (for [k (range 11)]
        (fibo k)) => '(0 1 1 2 3 5 8 13 21 34 55))

(fact "`fibo` ne consomme pas de pile."
      (try (count (str (fibo 100000N)))
        (catch java.lang.StackOverflowError _ :plus-de-pile))
      => 20899)

(fact "`fibo` est super rapide."
      (<= (Integer/parseInt
           (second (re-find #"(\d+)\."
                            (with-out-str (time (fibo 200000))))))
          2000)
      => true)



