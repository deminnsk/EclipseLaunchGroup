package com.serious.business.common;

import java.io.Serializable;

public class Pair<K, V> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4446947041855421840L;
	
	private final K first;
    private final V second;

    public static <K, V> Pair<K, V> createPair(K first, V second) {
        return new Pair<K, V>(first, second);
    }

	public Pair(K first, V second) {
		super();
		this.first = first;
		this.second = second;
	}

	public K getFirst() {
		return first;
	}

	public V getSecond() {
		return second;
	}
    
    


}