;; # Exercice 3 : Phrase-o-matic

(ns theme01-clojure.ex03-phrases)


;; => #'theme01-clojure.ex03-phrases/ex03-phrases

(defn gen-article
  "Générateur d'article."
  []
  (rand-nth ["le" "la" "un" "une"]))

;; => #'theme01-clojure.ex03-phrases/gen-article
(repeatedly 10 gen-article)

;; => ("une" "un" "une" "un" "la" "la" "un" "la" "une" "la")
;; ## Question 1 : générateurs d'adjectifs et de noms

(defn gen-adj[]
  (rand-nth ["bleu" "azur" "clair" "froid"]))

(defn gen-nom[]
  (rand-nth ["ciel" "mer" "riviere" "lac"]))

(repeatedly 10 gen-adj)
(repeatedly 10 gen-nom)

(gen-adj)

;; ## Question 2 : générateur de proposition nominale

(defn gen-prop-nominale []
  (str (gen-article) " " (gen-adj) " " (gen-nom)))

(gen-prop-nominale)

;; ## Question 3 : générateur de proposition verbale

(defn gen-verb[]
  (rand-nth ["parler" "danser" "chanter" "jouer"]))
(gen-verb)

(defn gen-propo-verbale[]
  (str (gen-verb) " " (gen-prop-nominal)))

;; ## Question 4 : générateur de phrases

;; ## Question 5 : phrase-o-matic++


