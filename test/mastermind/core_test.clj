(ns mastermind.core-test
  (:require [clojure.test :refer :all]
            [mastermind.core :refer :all]
            [midje.sweet :refer :all]))

;;On essaie la fonction valeur-tenta, qui prend en argument la valeur lue au clavier.
;;Il faut noter que les valeurs lues au clavier sont des strings, c'est pourquoi certaines valeurs sont entre ""
(facts
	(and
		(= (valeur-tenta "1") 12)
		(= (valeur-tenta "2") 10)
		(= (valeur-tenta "3") 8)
		(= (valeur-tenta "4") 6)) => true)


;;On essaie la fonction convertirKey qui permet de transformer un vecteur de String en un vecteur de clé.
;;Toutes les valeurs qui sont dans le vecteur String sont tapées au clavier, on les convertit pour pouvoir les comparer avec pionsC qui est un vecteur de clés des couleurs
;;Utilisables dans le jeu
(facts
	(and 
		(= (convertirKey ["red" "red" "red" "red"]) [:red :red :red :red])
		(= (convertirKey ["yellow"]) [:yellow])
		(= (convertirKey []))) => true)

;;On teste la fonction veriColor qui permet de vérifier si le vecteur donné par le player correspond au vecteur code secret
;;Lors du test, on verra apparaître sur le terminal les indications qui sont données au joueur.
;;Les valeurs données dans le vecteur Player ont été castées en key par la fonction convertirKey donnée ci-dessus
(facts
	(and
		(= (verifColor [:red :red :yellow :green] [:yellow :red :green :blue]) false)
		(= (verifColor [:red :yellow :white :black] [:red :yellow :white :black]) true)
		(= (verifColor [] []) true)) => true)



;;****************************************************************************************
;;Test repris de l'exercice 6 du tme

;;Pour la fonctions code-secret
(fact "Le `code-secret` a l'air aléatoire."
      (> (count (filter true? (map not=
                                   (repeatedly 20 #(code-secret 4))
                                   (repeatedly 20 #(code-secret 4)))))
         0)
      => true)



;;Pour la fonction indications
(fact "`indications` sont les bonnes."
      (indications [:rouge :rouge :vert :bleu]
                   [:vert :rouge :bleu :jaune])
      => [:color :good :color :bad]

      (indications [:rouge :rouge :vert :bleu]
                   [:bleu :rouge :vert :jaune])
      => [:color :good :good :bad]

      (indications [:rouge :rouge :vert :bleu]
                   [:rouge :rouge :vert :bleu])
      => [:good :good :good :good]

      (indications [:rouge :rouge :vert :vert]
                   [:vert :bleu :rouge :jaune])
      => [:color :bad :color :bad])



;;Pour la fonctions fréquences
(fact "les `frequences` suivantes sont correctes."
      (frequences [:rouge :rouge :vert :bleu :vert :rouge])
      => {:rouge 3 :vert 2 :bleu 1}

      (frequences [:rouge :vert :bleu])
      => {:rouge 1 :vert 1 :bleu 1}

      (frequences [1 2 3 2 1 4]) => {1 2, 2 2, 3 1, 4 1})


;;Pour la fonction freqs-dispo
(fact "Les fréquences disponibles de `freqs-dispo` sont correctes."
      (freqs-dispo [:rouge :rouge :bleu :vert :rouge]
                   [:good :color :bad :good :color])
      => {:bleu 1, :rouge 2, :vert 0})



;;Pour la fonction filtre-indications
(fact "Le `filtre-indications` fonctionne bien."
      (filtre-indications [:rouge :rouge :vert :bleu]
                          [:vert :rouge :bleu :jaune]
                          [:color :good :color :bad])
      => [:color :good :color :bad]

      (filtre-indications [:rouge :vert :rouge :bleu]
                          [:rouge :rouge :bleu :rouge]
                          [:good :color :color :color])
      => [:good :color :color :bad])

