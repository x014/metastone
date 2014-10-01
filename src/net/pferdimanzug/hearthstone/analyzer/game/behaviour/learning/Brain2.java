package net.pferdimanzug.hearthstone.analyzer.game.behaviour.learning;

import java.util.List;

import net.pferdimanzug.hearthstone.analyzer.game.GameContext;
import net.pferdimanzug.hearthstone.analyzer.game.Player;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Minion;
import net.pferdimanzug.hearthstone.analyzer.utils.MathUtils;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.Layer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.util.simple.EncogUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Brain2 implements IBrain {

	private static Logger logger = LoggerFactory.getLogger(Brain2.class);

	private static final int INPUTS = 15;
	private static final int HIDDEN_NEURONS = 40;
	private static final int OUTPUTS = 1;

	private static final double ALPHA = 0.1;
	private static final double LAMBDA = 0.01;

	private BasicNetwork neuralNetwork;
	private boolean learning;

	public Brain2() {
		// Create a neural network, using the utility.
		neuralNetwork = EncogUtility.simpleFeedForward(INPUTS, HIDDEN_NEURONS, 0, 1, true);
		//List<Layer> layers = neuralNetwork.getStructure().getLayers();
		//layers.get(layers.size() - 1).setActivation(new ActivationSigmoid());
		neuralNetwork.reset();
	}

	@Override
	public double[] getOutput(GameContext context, int playerId) {
		double[] input = gameStateToInput(context, playerId);
		double[] output = new double[OUTPUTS];
		neuralNetwork.compute(input, output);
		return output;
	}

	private double[] gameStateToInput(GameContext context, int playerId) {
		double[] input = new double[INPUTS];
		Player player = context.getPlayer(playerId);
		Player opponent = context.getOpponent(player);
		encodePlayer(player, input, 0);
		encodePlayer(opponent, input, INPUTS / 2);
		input[INPUTS - 1] = MathUtils.clamp01(context.getTurn() / 20.0);
		return input;
	}

	private void encodePlayer(Player player, double[] data, int offset) {
		List<Minion> minions = player.getMinions();
		int totalMinionAttack = 0;
		int totalMinionHp = 0;
		for (int i = 0; i < 7; i++) {
			Minion minion = i < minions.size() ? player.getMinions().get(i) : null;
			totalMinionAttack += minion != null ? minion.getAttack() : 0;
			totalMinionHp += minion != null ? minion.getHp() : 0;
		}
		data[offset++] = minions.size() / 7.0;
		data[offset++] = MathUtils.clamp01(totalMinionAttack / 40.0);
		data[offset++] = MathUtils.clamp01(totalMinionHp / 40.0);
		data[offset++] = MathUtils.clamp01(player.getHero().getAttack() / 10.0);
		data[offset++] = MathUtils.clamp01((player.getHero().getHp() + player.getHero().getArmor()) / 30.0);
		data[offset++] = player.getHand().getCount() / 10.0;
		data[offset++] = MathUtils.clamp01(player.getDeck().getCount() / 30.0);
	}


	@Override
	public double getEstimatedUtility(double[] output) {
		return output[0];
	}

	@Override
	public void learn(GameContext originalState, int playerId, double[] nextOutput, double reward) {
		double[] input = gameStateToInput(originalState, playerId);
		double[] output = getOutput(originalState, playerId);
		double[] error = new double[OUTPUTS];
		for (int i = 0; i < error.length; i++) {
			error[i] = reward + nextOutput[i] - output[i];
		}

		MLDataSet training = new BasicMLDataSet(new double[][] {input}, new double[][] {error});
		final MLTrain train =  new Backpropagation(neuralNetwork, training, ALPHA, 0.3);
		train.iteration();
	}

	@Override
	public boolean isLearning() {
		return learning;
	}

	@Override
	public void setLearning(boolean learning) {
		this.learning = learning;
	}

	@Override
	public void save(String savePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(String savePath) {
		// TODO Auto-generated method stub
		
	}

}