package ru.sstu.sm.core.service;

import java.io.File;
import java.util.Observable;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

import org.w3c.dom.Node;
import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.service.solver.AbstractHardTypeSolver;
import ru.sstu.sm.core.service.solver.AbstractSolver;
import ru.sstu.sm.core.service.solver.FreeTypeSolver;

/**
 * <code>AbstractEngine</code> encapsulates business logic of Strength of
 * Materials task solver. The class is abstract and extends {@link Observable}.
 * <code>AbstractEngine</code> should be extended by every concrete engine.
 *
 * @author Denis Murashev
 * @param <T> type of section
 * @since SM 1.0
 */
public abstract class AbstractEngine<T extends Section> extends Observable {

	/**
	 * Configuration.
	 */
	private Config<T> config;

	/**
	 * Configuration manager.
	 */
	private ConfigManager<T> configManager;

	/**
	 * Initializes configuration.
	 *
	 * @param config configuration
	 */
	protected AbstractEngine(Config<T> config) {
		this.config = config;
	}

	/**
	 * Solves task.
	 */
	public void calculate() {
		AbstractSolver<T> solver = getSolver();
		if (solver != null) {
			solver.calculate();
		}
		updateAll();
	}

	/**
	 * Provides current configuration {@link Config}.
	 *
	 * @return configuration
	 */
	public final Config<T> getConfig() {
		return config;
	}

	/**
	 * Sets configuration.
	 *
	 * @param config configuration
	 */
	public final void setConfig(Config<T> config) {
		this.config = config;
	}

	/**
	 * Adds section to configuration.
	 *
	 * @param section section.
	 */
	public final void addSection(T section) {
		config.add(section);
		updateAll();
	}

	/**
	 * Sets section.
	 *
	 * @param index   position
	 * @param section section
	 */
	public final void setSection(int index, T section) {
		config.set(index, section);
		updateAll();
	}

	/**
	 * Deletes section from given position.
	 *
	 * @param index position
	 */
	public final void deleteSection(int index) {
		config.remove(index);
		updateAll();
	}

	/**
	 * Clears configuration.
	 */
	public final void clearConfig() {
		config.clear();
		updateAll();
	}

	/**
	 * Loads configuration from given file.
	 *
	 * @param document document
	 * @throws SMException if some error occurs
	 */
	public final void loadConfig(Node document) throws SMException {
		config = configManager.load(document);
		calculate();
		updateAll();
	}

	/**
	 * Saves configuration in file.
	 *
	 * @param file file
	 * @throws SMException if some error occurs
	 */
	public final void saveConfig(File file) throws SMException {
		configManager.save(config, file);
	}

	/**
	 * Provides sum of influences at given section.
	 *
	 * @param index index of section
	 * @return sum of influences
	 */
	public abstract PolynomialFunction getRestInfluence(int index);

	/**
	 * Sets XML configuration manager.
	 *
	 * @param configManager configuration manager
	 */
	protected void setConfigManager(ConfigManager<T> configManager) {
		this.configManager = configManager;
	}

	/**
	 * Provides active solver.
	 *
	 * @return active solver
	 */
	protected final AbstractSolver<T> getSolver() {
		if (config.getType() == Config.Type.FREE) {
			return new FreeTypeSolver<T>(this);
		}
		return getHardTypeSolver();
	}

	/**
	 * Provides statically unsolvable solver if necessary.
	 *
	 * @return statically unsolvable solver
	 */
	protected abstract AbstractHardTypeSolver<T> getHardTypeSolver();

	/**
	 * Updates all observers.
	 */
	protected abstract void updateAll();
}
