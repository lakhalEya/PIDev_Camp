package tn.camps.tuncamps.service.ecomerce;

import java.util.Optional;

public abstract class Abstarctservices<t> {
	public abstract Iterable<t> findAll();

	public abstract Optional<t> findbyid(int id);

	public abstract boolean Delete(int id);

	public abstract t update(t a);

	public abstract t create(t a);

}