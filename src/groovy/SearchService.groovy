/*
* SearchService.groovy
* Going to contain all logic for searches performed
*/

package itinerate

import grails.gorm.*

class SearchService {
	
	def performSearch(searchParams) {
		def possibleEvents = trimSearches(searchParams);
		def results = searchByKeyword(possibleEvents, searchParams.keyword);
		return results;
	}

	def trimSearches(filterParams) {
		def events = Events.list();
		def trimmedResults = [];
		for(event in events) {
			Boolean okayToAdd = false;
			if(filterParams.price != null) {
				okayToAdd = event.pricing.adultPrice >= filterParams.price ? true : false;
			}

			if(filterParams.reviews != null) {
				okayToAdd = event.reviews.size() >= filterParams.reviews ? true : false;
			}

			if(filterParams.stars != null) {
				okayToAdd = findAverageOfRatings(event.ratings) >= filterParams.stars ? true : false;
			}

			okayToAdd = event.address.contains(filterParams.location) ? true : false;

			if(okayToAdd) {
				trimmedResults.add(event);
			}
		}
		return trimmedResults;
	}

	def searchByKeyword(possibleEvents, keyword) {
		def results = [];
		for(event in possibleEvents) {
			if(event.name.contains(keyword)) {
				results.add(event);
			}
		}
		return results;
	}

	def findAverageOfRatings(ratings) {
		def total = 0.0;
		for(rating in ratings) {
			total += rating.rating;
		}
		return (total / ratings.size());
	}
}