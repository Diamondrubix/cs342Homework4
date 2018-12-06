/* Author: Alexander Oey (aoey2) */
package artifact;

import artifact.Artifact;
import artifact.Equippable;
import artifact.EquippableType;

import java.util.Scanner;

/**
 * This class represents an Artifact that is Equippable by characters.
 *
 * @author Alexander Oey (aoey2) 
 */
public abstract class EquippableArtifact extends Artifact implements Equippable {
	enum EquipmentType implements EquippableType {
		NOTYPE,
		HEAD,
		BODY,
		HAND,
		FOOT;
	}
	
	protected EquipmentType equipType;
	
	protected EquippableArtifact(Scanner sc, int version) {
		super(sc,version);
	}
	
	public EquipmentType getEquipType() {
		return equipType;
	}
	
	public boolean isEquipType(EquippableType type) {
		return (type instanceof EquipmentType) && (equipType == (EquipmentType) type);
	}
} 