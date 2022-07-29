package com.example.movieapp.UI.Actors

class ActorMapper {
    fun map(actorResponse: ActorResponse): Actor {
        return Actor(
            id = actorResponse.id,
            name = actorResponse.name,
            isSelected = false
        )
    }
}