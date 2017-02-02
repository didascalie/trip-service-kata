#Test sur code legacy

Couvrez ce code avec tests autant que possible. Ensuite refactorez le code à votre goût. Idéalement la classe TripService doit être testé unitairement en totalité


##Contraintes 
* ne pas changer le code sans avoir un test pour qui couvre ce bout.
* si on doit changer le code pour écrire des tests, ce faire __uniquement__ 
avec refactorings automatisés.


##Indices
...                               
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
  
* Spy ou sous-classer TripService
* Ajout de variable d'instance
* Ajout de paramètre à la méthode
* Encapsuler méthode statique par méthode d'instance   


Merci à Sandro pour son exemple dans le billet [Testing legacy code: Hard-wired dependencies][1]
[1]: http://craftedsw.blogspot.com/2011/07/testing-legacy-hard-wired-dependencies.html "Testing legacy code: Hard-wired dependencies blog post"

=======
[1]: http://codurance.com/2011/07/16/testing-legacy-hard-wired-dependencies/ "Testing legacy code: Hard-wired dependencies blog post"
[2]: https://github.com/sandromancuso/trip-service-kata/blob/master/java/trip-service-kata/src/main/java/org/craftedsw/tripservicekata/TripService_Original.java "TripService_Original.java"
[3]: https://github.com/sandromancuso/trip-service-kata/blob/master/java/trip-service-kata/src/test/java/org/craftedsw/tripservicekata/TripServiceTest.java "TripServiceTest.java"
[4]: https://github.com/sandromancuso/trip-service-kata/blob/master/java/trip-service-kata/src/main/java/org/craftedsw/tripservicekata/trip/TripService.java "TripService.java"
