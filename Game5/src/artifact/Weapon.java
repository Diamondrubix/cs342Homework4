/* Author: Alexander Oey (aoey2) */
package artifact;

import game.CleanLineScanner;
import game.Game;
import place.Place;
import character.Character;
import artifact.Artifact;
import artifact.EquippableArtifact;
import artifact.EquippableType;


import java.util.Scanner;

/**
 * This class represents an Artifact that is wearable on a character.
 *
 *	These are ID codes that will be used in GDF 5.1 file
 *	~ 0 ~ Melee Weapon
 *	~ 1 ~ Ranged Weapon
 *	~ 2 ~ Magic Weapon
 *
 * @author Alexander Oey (aoey2)
 */
public class Weapon extends EquippableArtifact {
	/**
	 * Represents the type of weapon encapsulated by the Weapon class.
	 */
	public enum WeaponType implements EquippableType {
		NOTWEAPON, // should never be used.
		MELEE,
		RANGED,
		MAGIC;
	}
	
	//Fields
	private final WeaponType weaponType;
	private final int damage;
	
	/**
	 * Constructs an armor given a Scanner to a file.
	 *
	 * @param sc Scanner instance
	 */
	public Weapon(Scanner sc, int version) {
		super(sc, version);
		String[] tokens = CleanLineScanner.getTokens(sc);
		switch(Integer.parseInt(tokens[0])) {
			case 0: weaponType = WeaponType.MELEE; break;
			case 1: weaponType = WeaponType.RANGED; break;
			case 2: weaponType = WeaponType.MAGIC; break;
			default: weaponType = WeaponType.NOTWEAPON; break;
		}
		this.damage = Integer.parseInt(tokens[1]);
		// System.out.println("token[0] : "+tokens[0]);
	}
	
	/**
	 * Returns the type of weapon.
	 *
	 * @return type of armor in WeaponType
	 */
	public WeaponType getType() {
		return weaponType;
	}
	
	/**
	 * Returns the damage of the weapon.
	 *
	 * @return the amount of damage dealt by this weapon
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 *
	 */
	@Override
	public boolean isEquipType(EquippableType type) {
		if (type instanceof WeaponType) {
			return (weaponType == (WeaponType) type);
		}
		return false;
	}
}