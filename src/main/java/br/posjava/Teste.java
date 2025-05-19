package br.posjava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Teste {

    private static BDVeiculos bdVeiculos = new BDVeiculos();
    private static JFrame janela = new JFrame("Gestao de Veiculos");
    private static Dimension tamanhoBotao = new Dimension(250, 35);

    public static void main(String[] args) {
        telaInicial();

    }

    private static boolean validarCadastroPlaca(String placa) throws VeicExistException {
        for (Passeio passeio : bdVeiculos.getListaPasseio()) {
            if (passeio != null && passeio.getPlaca().equals(placa)) {
                throw new VeicExistException();
            }
        }

        for (Carga carga : bdVeiculos.getListaCarga()) {
            if (carga != null && carga.getPlaca().equals(placa)) {
                throw new VeicExistException();
            }
        }

        return false;
    }

    // metodos de manipulacao de passeio
    private static void cadastrarVeiculoPasseio(Passeio veiculo) throws VeicExistException, VelocException {

        if (!validarCadastroPlaca(veiculo.getPlaca())) {
            if (veiculo.getVelocMax() < 80 || veiculo.getVelocMax() > 110) {
                veiculo.setVelocMax(100);
                bdVeiculos.getListaPasseio().add(veiculo);
                JOptionPane.showMessageDialog(null, "Veiculo de passeio cadastrado com sucesso","Confirmação de Cadastro", JOptionPane.INFORMATION_MESSAGE);
                throw new VelocException();

            } else {
                veiculo.setVelocMax(veiculo.getVelocMax());
                bdVeiculos.getListaPasseio().add(veiculo);
                JOptionPane.showMessageDialog(null, "Veiculo de passeio cadastrado com sucesso","Confirmação de Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static Passeio encontrarVeiculoPasseio(String placa) {
        for (Passeio veiculo : bdVeiculos.getListaPasseio()) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) return veiculo;
        }

        return null;
    }

    private static boolean excluirVeiculoPasseioPlaca(String placa) {
        Passeio veiculo = encontrarVeiculoPasseio(placa);

        if (veiculo != null) {
            bdVeiculos.getListaPasseio().remove(veiculo);
            return true;
        }

        return false;
    }

    private static java.util.List<Passeio> imprimirVeiculoPasseioPlaca() {
        if (bdVeiculos.getListaPasseio().isEmpty()) return null;
        return bdVeiculos.getListaPasseio();

    }

    // metodos de manipulacao de carga
    private static void cadastrarVeiculoCarga(Carga veiculo) throws VeicExistException, VelocException {

        if (!validarCadastroPlaca(veiculo.getPlaca())) {
            if (veiculo.getVelocMax() < 80 || veiculo.getVelocMax() > 110) {

                veiculo.setVelocMax(90);
                bdVeiculos.getListaCarga().add(veiculo);

                JOptionPane.showMessageDialog(null, "Veiculo de carga cadastrado com sucesso","Confirmação de Cadastro", JOptionPane.INFORMATION_MESSAGE);
                throw new VelocException();
            } else {
                veiculo.setVelocMax(veiculo.getVelocMax());
                bdVeiculos.getListaCarga().add(veiculo);
                JOptionPane.showMessageDialog(null, "Veiculo de carga cadastrado com sucesso","Confirmação de Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    private static Carga encontrarVeiculoCarga(String placa) {

        for (Carga veiculo : bdVeiculos.getListaCarga()) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) return veiculo;
        }

        return null;
    }

    private static boolean excluirVeiculoCargaPlaca(String placa) {
        Carga veiculo = encontrarVeiculoCarga(placa);

        if (veiculo != null) {
            bdVeiculos.getListaCarga().remove(veiculo);
            return true;
        }

        return false;
    }

    private static java.util.List<Carga> imprimirVeiculoCargaPlaca() {
        if (bdVeiculos.getListaCarga().isEmpty()) return null;
        return bdVeiculos.getListaCarga();
    }

// -----------------------------------------------------------------------------------

    public static void telaInicial() {
        janela.getContentPane().removeAll();
        janela.setSize(400, 100);
        janela.setLayout(new BoxLayout(janela.getContentPane(), BoxLayout.Y_AXIS));
        janela.setTitle("Gestao de Veiculos");

        JButton btPasseio = new JButton("Passeio");
        btPasseio.setMaximumSize(tamanhoBotao);
        btPasseio.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btPasseio);

        JButton btCarga = new JButton("Carga");
        btCarga.setMaximumSize(tamanhoBotao);
        btCarga.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btCarga);

        btPasseio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicialPasseio();
            }
        });

        btCarga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicialCarga();
            }
        });

        janela.setDefaultCloseOperation(janela.EXIT_ON_CLOSE);
        janela.setVisible(true);
        janela.revalidate();
        janela.repaint();
    }

    public static void telaInicialCarga() {
        janela.getContentPane().removeAll();
        janela.setSize(400, 200);
        janela.setLayout(new BoxLayout(janela.getContentPane(), BoxLayout.Y_AXIS));
        janela.setTitle("Gestao de Veiculos de Carga");

        JButton btCadastrarCarga = new JButton("Cadastrar");
        btCadastrarCarga.setMaximumSize(tamanhoBotao);
        btCadastrarCarga.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btCadastrarCarga);
        janela.add(Box.createVerticalStrut(10));

        JButton btConsultarExcluirCargaPelaPlaca = new JButton("Consultar/Excluir pela placa");
        btConsultarExcluirCargaPelaPlaca.setMaximumSize(tamanhoBotao);
        btConsultarExcluirCargaPelaPlaca.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btConsultarExcluirCargaPelaPlaca);
        janela.add(Box.createVerticalStrut(10));

        JButton btImprimirExcluirCargaTodos = new JButton("Imprimir/Excluir todos");
        btImprimirExcluirCargaTodos.setMaximumSize(tamanhoBotao);
        btImprimirExcluirCargaTodos.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btImprimirExcluirCargaTodos);
        janela.add(Box.createVerticalStrut(10));

        JButton btCargaSair = new JButton("Sair");
        btCargaSair.setMaximumSize(tamanhoBotao);
        btCargaSair.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btCargaSair);

        btCadastrarCarga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaCadastrarCarga();
            }
        });

        btConsultarExcluirCargaPelaPlaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaConsultarExcluirCargaPelaPlaca();
            }
        });

        btImprimirExcluirCargaTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaImprimirExcluirCargaTodos();
            }
        });

        btCargaSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicial();
            }
        });

        janela.revalidate();
        janela.repaint();
    }

    public static void telaCadastrarCarga() {
        janela.getContentPane().removeAll();
        janela.setSize(600, 400);
        janela.setLayout(new GridLayout(0, 2, 10, 5));
        janela.setTitle("Cadastrar Veiculo de Carga");

        JLabel labelPlaca = new JLabel("Placa:");
        JTextField cxPlaca = new JTextField(30);
        janela.add(labelPlaca);
        janela.add(cxPlaca);

        JLabel labelMarca = new JLabel("Marca:");
        JTextField cxMarca = new JTextField(30);
        janela.add(labelMarca);
        janela.add(cxMarca);

        JLabel labelModelo = new JLabel("Modelo:");
        JTextField cxModelo = new JTextField(30);
        janela.add(labelModelo);
        janela.add(cxModelo);

        JLabel labelCor = new JLabel("Cor:");
        JTextField cxCor = new JTextField(30);
        janela.add(labelCor);
        janela.add(cxCor);

        JLabel labelVelocMax = new JLabel("Velocidade maxima:");
        JTextField cxVelocMax = new JTextField(30);
        janela.add(labelVelocMax);
        janela.add(cxVelocMax);

        JLabel labelQtdRodas = new JLabel("Quantidade rodas:");
        JTextField cxQtdRodas = new JTextField(10);
        janela.add(labelQtdRodas);
        janela.add(cxQtdRodas);

        JLabel labelQtdPistoes = new JLabel("Quantidade de pistoes:");
        JTextField cxQtdPistoes = new JTextField(13);
        janela.add(labelQtdPistoes);
        janela.add(cxQtdPistoes);

        JLabel labelPotencia = new JLabel("Potencia:");
        JTextField cxPotencia = new JTextField(30);
        janela.add(labelPotencia);
        janela.add(cxPotencia);

        JLabel labelCargaMax = new JLabel("Carga maxima:");
        JTextField cxCargaMax = new JTextField(30);
        janela.add(labelCargaMax);
        janela.add(cxCargaMax);

        JLabel labelTara = new JLabel("Tara:");
        JTextField cxTara = new JTextField(30);
        janela.add(labelTara);
        janela.add(cxTara);

        JButton btCadastrar = new JButton("Cadastrar");
        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Carga veiculo = new Carga();
                veiculo.setPlaca(cxPlaca.getText());
                veiculo.setMarca(cxMarca.getText());
                veiculo.setModelo(cxModelo.getText());
                veiculo.setCor(cxCor.getText());
                veiculo.setVelocMax(Float.parseFloat(cxVelocMax.getText()));
                veiculo.setQtdRodas(Integer.parseInt(cxQtdRodas.getText()));
                veiculo.getMotor().setQtdPist(Integer.parseInt(cxQtdPistoes.getText()));
                veiculo.getMotor().setPotencia(Integer.parseInt(cxPotencia.getText()));
                veiculo.setCargaMax(Integer.parseInt(cxCargaMax.getText()));
                veiculo.setTara(Integer.parseInt(cxTara.getText()));

                try {
                    cadastrarVeiculoCarga(veiculo);
                } catch (VeicExistException veie) {
                    JOptionPane.showMessageDialog(null, veie.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                } catch (VelocException veloe) {
                    JOptionPane.showMessageDialog(null, veloe.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton btLimpar = new JButton("Limpar");
        btLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cxPlaca.setText("");
                cxMarca.setText("");
                cxModelo.setText("");
                cxCor.setText("");
                cxVelocMax.setText("");
                cxQtdRodas.setText("");
                cxQtdPistoes.setText("");
                cxPotencia.setText("");
                cxCargaMax.setText("");
                cxTara.setText("");
            }
        });

        JButton btNovo = new JButton("Novo");
        btNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cxPlaca.setText("");
                cxMarca.setText("");
                cxModelo.setText("");
                cxCor.setText("");
                cxVelocMax.setText("");
                cxQtdRodas.setText("");
                cxQtdPistoes.setText("");
                cxPotencia.setText("");
                cxCargaMax.setText("");
                cxTara.setText("");

                cxPlaca.requestFocusInWindow();
            }
        });

        JButton btSair = new JButton("Sair");
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicialCarga();
            }
        });

        janela.add(btCadastrar);
        janela.add(btLimpar);
        janela.add(btNovo);
        janela.add(btSair);

        janela.revalidate();
        janela.repaint();
    }

    public static void telaConsultarExcluirCargaPelaPlaca() {
        janela.getContentPane().removeAll();
        janela.setSize(600, 400);
        janela.setLayout(new GridLayout(0, 2, 10, 5));
        janela.setTitle("Consultar/Excluir Veiculo de Carga pela Placa");

        janela.add(new JLabel("Informe a placa:"));
        JTextField cxPlaca = new JTextField(30);
        janela.add(cxPlaca);

        janela.add(new JLabel("Tara:"));
        JTextField cxTara = new JTextField(30);
        cxTara.setEditable(false);
        cxTara.setVisible(true);
        janela.add(cxTara);

        janela.add(new JLabel("Carga maxima:"));
        JTextField cxCargaMax = new JTextField(30);
        cxCargaMax.setEditable(false);
        cxTara.setVisible(true);
        janela.add(cxCargaMax);

        janela.add(new JLabel("Marca:"));
        JTextField cxMarca = new JTextField(30);
        cxMarca.setEditable(false);
        cxMarca.setVisible(true);
        janela.add(cxMarca);

        janela.add(new JLabel("Modelo:"));
        JTextField cxModelo = new JTextField(30);
        cxModelo.setEditable(false);
        cxModelo.setVisible(true);
        janela.add(cxModelo);

        janela.add(new JLabel("Cor:"));
        JTextField cxCor = new JTextField(30);
        cxCor.setEditable(false);
        cxCor.setVisible(true);
        janela.add(cxCor);

        janela.add(new JLabel("Quantidade rodas:"));
        JTextField cxQtdRodas = new JTextField(10);
        cxQtdRodas.setEditable(false);
        cxQtdRodas.setVisible(true);
        janela.add(cxQtdRodas);

        janela.add(new JLabel("Velocidade maxima:"));
        JTextField cxVelocMax = new JTextField(30);
        cxVelocMax.setEditable(false);
        cxVelocMax.setVisible(true);
        janela.add(cxVelocMax);

        janela.add(new JLabel("Quantidade de pistoes:"));
        JTextField cxQtdPistoes = new JTextField(13);
        cxQtdPistoes.setEditable(false);
        cxQtdPistoes.setVisible(true);
        janela.add(cxQtdPistoes);

        janela.add(new JLabel("Potencia:"));
        JTextField cxPotencia = new JTextField(30);
        cxPotencia.setEditable(false);
        cxPotencia.setVisible(true);
        janela.add(cxPotencia);

        JButton btConsultar = new JButton("Consultar");
        btConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Carga veiculo = encontrarVeiculoCarga(cxPlaca.getText());

                if (veiculo != null) {
                    cxPlaca.setText(veiculo.getPlaca());
                    cxMarca.setText(veiculo.getMarca());
                    cxModelo.setText(veiculo.getModelo());
                    cxCor.setText(veiculo.getCor());
                    cxVelocMax.setText(String.valueOf(veiculo.getVelocMax()));
                    cxQtdRodas.setText(String.valueOf(veiculo.getQtdRodas()));
                    cxQtdPistoes.setText(String.valueOf(veiculo.getMotor().getQtdPist()));
                    cxPotencia.setText(String.valueOf(veiculo.getMotor().getPotencia()));
                    cxCargaMax.setText(String.valueOf(veiculo.getCargaMax()));
                    cxTara.setText(String.valueOf(veiculo.getTara()));
                } else {
                    JOptionPane.showMessageDialog(null, "Placa não existe no sistema", " Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        janela.add(btConsultar);

        JButton btExcluir = new JButton("Excluir");
        btExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean resultado = excluirVeiculoCargaPlaca(cxPlaca.getText());

                if (resultado) {
                    cxPlaca.setText("");
                    cxMarca.setText("");
                    cxModelo.setText("");
                    cxCor.setText("");
                    cxVelocMax.setText("");
                    cxQtdRodas.setText("");
                    cxQtdPistoes.setText("");
                    cxPotencia.setText("");
                    cxCargaMax.setText("");
                    cxTara.setText("");

                    JOptionPane.showMessageDialog(null, "Veículo de carga excluído com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro na exclusão do veículo", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        janela.add(btExcluir);



        JButton btSair = new JButton("Sair");
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicialCarga();
            }
        });
        janela.add(btSair);

        janela.revalidate();
        janela.repaint();
    }

    public static void telaImprimirExcluirCargaTodos() {
        janela.getContentPane().removeAll();
        janela.setSize(800, 400);
        janela.setLayout(new BorderLayout());
        janela.setTitle("Imprimir/Excluir todos os Veículos de Carga");

        // colunas da tabela
        String[] colunas = { "Placa", "Marca", "Modelo", "Cor", "Veloc. Máx.", "Qtd. Rodas", "Pistões", "Potência", "Carga Máx.", "Tara" };

        // Inicializa tabela vazia
        String[][] dadosVazios = new String[0][colunas.length];
        JTable tabela = new JTable(dadosVazios, colunas);
        JScrollPane scroll = new JScrollPane(tabela);
        janela.add(scroll, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        // Botão Imprimir Todos
        JButton btImprimirTodos = new JButton("Imprimir Todos");
        btImprimirTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Carga> listaCargas = imprimirVeiculoCargaPlaca();

                if (listaCargas == null || listaCargas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum veículo de carga cadastrado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Preenche os dados da tabela com a lista
                String[][] dados = new String[listaCargas.size()][10];
                for (int i = 0; i < listaCargas.size(); i++) {
                    Carga v = listaCargas.get(i);
                    dados[i][0] = v.getPlaca();
                    dados[i][1] = v.getMarca();
                    dados[i][2] = v.getModelo();
                    dados[i][3] = v.getCor();
                    dados[i][4] = String.valueOf(v.getVelocMax());
                    dados[i][5] = String.valueOf(v.getQtdRodas());
                    dados[i][6] = String.valueOf(v.getMotor().getQtdPist());
                    dados[i][7] = String.valueOf(v.getMotor().getPotencia());
                    dados[i][8] = String.valueOf(v.getCargaMax());
                    dados[i][9] = String.valueOf(v.getTara());
                }

                // Atualiza a tabela com os novos dados
                tabela.setModel(new DefaultTableModel(dados, colunas));
            }
        });

        JButton btExcluirTodos = new JButton("Excluir Todos");
        btExcluirTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir todos os veículos de carga?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    bdVeiculos.getListaCarga().clear();
                    tabela.setModel(new DefaultTableModel(new String[0][colunas.length], colunas));
                    JOptionPane.showMessageDialog(null, "Veículos de carga excluídos com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton btSair = new JButton("Sair");
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicialCarga();
            }
        });

        painelBotoes.add(btImprimirTodos);
        painelBotoes.add(btExcluirTodos);
        painelBotoes.add(btSair);

        janela.add(painelBotoes, BorderLayout.SOUTH);

        janela.revalidate();
        janela.repaint();
    }

// -----------------------------------------------------------------------------------

    public static void telaInicialPasseio() {
        janela.getContentPane().removeAll();
        janela.setSize(400, 200);
        janela.setLayout(new BoxLayout(janela.getContentPane(), BoxLayout.Y_AXIS));
        janela.setTitle("Gestao de Veiculos de Passeio");

        JButton btCadastrarPasseio = new JButton("Cadastrar");
        btCadastrarPasseio.setMaximumSize(tamanhoBotao);
        btCadastrarPasseio.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btCadastrarPasseio);
        janela.add(Box.createVerticalStrut(10));

        JButton btConsultarExcluirPasseioPelaPlaca = new JButton("Consultar/Excluir pela placa");
        btConsultarExcluirPasseioPelaPlaca.setMaximumSize(tamanhoBotao);
        btConsultarExcluirPasseioPelaPlaca.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btConsultarExcluirPasseioPelaPlaca);
        janela.add(Box.createVerticalStrut(10));

        JButton btImprimirExcluirCargaTodos = new JButton("Imprimir/Excluir todos");
        btImprimirExcluirCargaTodos.setMaximumSize(tamanhoBotao);
        btImprimirExcluirCargaTodos.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btImprimirExcluirCargaTodos);
        janela.add(Box.createVerticalStrut(10));

        JButton btCargaSair = new JButton("Sair");
        btCargaSair.setMaximumSize(tamanhoBotao);
        btCargaSair.setAlignmentX(Component.CENTER_ALIGNMENT);
        janela.add(btCargaSair);

        btCadastrarPasseio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaCadastrarPasseio();
            }
        });

        btConsultarExcluirPasseioPelaPlaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaConsultarExcluirPasseioPelaPlaca();
            }
        });

        btImprimirExcluirCargaTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                janela.getContentPane().removeAll();
                telaImprimirExcluirPasseioTodos();
            }
        });

        btCargaSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicial();
            }
        });

        janela.revalidate();
        janela.repaint();
    }

    public static void telaCadastrarPasseio() {
        janela.getContentPane().removeAll();
        janela.setSize(600, 400);
        janela.setLayout(new GridLayout(0, 2, 10, 5));
        janela.setTitle("Cadastrar Veiculo de Passeio");

        JLabel labelPlaca = new JLabel("Placa:");
        JTextField cxPlaca = new JTextField(30);
        janela.add(labelPlaca);
        janela.add(cxPlaca);

        JLabel labelMarca = new JLabel("Marca:");
        JTextField cxMarca = new JTextField(30);
        janela.add(labelMarca);
        janela.add(cxMarca);

        JLabel labelModelo = new JLabel("Modelo:");
        JTextField cxModelo = new JTextField(30);
        janela.add(labelModelo);
        janela.add(cxModelo);

        JLabel labelCor = new JLabel("Cor:");
        JTextField cxCor = new JTextField(30);
        janela.add(labelCor);
        janela.add(cxCor);

        JLabel labelVelocMax = new JLabel("Velocidade maxima:");
        JTextField cxVelocMax = new JTextField(30);
        janela.add(labelVelocMax);
        janela.add(cxVelocMax);

        JLabel labelQtdRodas = new JLabel("Quantidade rodas:");
        JTextField cxQtdRodas = new JTextField(10);
        janela.add(labelQtdRodas);
        janela.add(cxQtdRodas);

        JLabel labelQtdPistoes = new JLabel("Quantidade de pistoes:");
        JTextField cxQtdPistoes = new JTextField(13);
        janela.add(labelQtdPistoes);
        janela.add(cxQtdPistoes);

        JLabel labelPotencia = new JLabel("Potencia:");
        JTextField cxPotencia = new JTextField(30);
        janela.add(labelPotencia);
        janela.add(cxPotencia);

        JLabel labelQtdPassageiros = new JLabel("Qtd. Passageiros:");
        JTextField cxQtdPassageiros = new JTextField(30);
        janela.add(labelQtdPassageiros);
        janela.add(cxQtdPassageiros);

        JButton btCadastrar = new JButton("Cadastrar");
        btCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Passeio veiculo = new Passeio();
                veiculo.setPlaca(cxPlaca.getText());
                veiculo.setMarca(cxMarca.getText());
                veiculo.setModelo(cxModelo.getText());
                veiculo.setCor(cxCor.getText());
                veiculo.setVelocMax(Float.parseFloat(cxVelocMax.getText()));
                veiculo.setQtdRodas(Integer.parseInt(cxQtdRodas.getText()));
                veiculo.getMotor().setQtdPist(Integer.parseInt(cxQtdPistoes.getText()));
                veiculo.getMotor().setPotencia(Integer.parseInt(cxPotencia.getText()));
                veiculo.setQtdPassageiros(Integer.parseInt(cxQtdPassageiros.getText()));

                try {
                    cadastrarVeiculoPasseio(veiculo);
                } catch (VeicExistException veie) {
                    JOptionPane.showMessageDialog(null, veie.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

                } catch (VelocException veloe) {
                    JOptionPane.showMessageDialog(null, veloe.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton btLimpar = new JButton("Limpar");
        btLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cxPlaca.setText("");
                cxMarca.setText("");
                cxModelo.setText("");
                cxCor.setText("");
                cxVelocMax.setText("");
                cxQtdRodas.setText("");
                cxQtdPistoes.setText("");
                cxPotencia.setText("");
                cxQtdPassageiros.setText("");
            }
        });

        JButton btNovo = new JButton("Novo");
        btNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cxPlaca.setText("");
                cxMarca.setText("");
                cxModelo.setText("");
                cxCor.setText("");
                cxVelocMax.setText("");
                cxQtdRodas.setText("");
                cxQtdPistoes.setText("");
                cxPotencia.setText("");
                cxQtdPassageiros.setText("");

                cxPlaca.requestFocusInWindow();
            }
        });

        JButton btSair = new JButton("Sair");
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicialPasseio();
            }
        });

        janela.add(btCadastrar);
        janela.add(btLimpar);
        janela.add(btNovo);
        janela.add(btSair);

        janela.revalidate();
        janela.repaint();
    }

    public static void telaConsultarExcluirPasseioPelaPlaca() {
        janela.getContentPane().removeAll();
        janela.setSize(600, 400);
        janela.setLayout(new GridLayout(0, 2, 10, 5));
        janela.setTitle("Consultar/Excluir Veiculo de Passeio pela Placa");

        janela.add(new JLabel("Informe a placa:"));
        JTextField cxPlaca = new JTextField(30);
        janela.add(cxPlaca);

        janela.add(new JLabel("Marca:"));
        JTextField cxMarca = new JTextField(30);
        cxMarca.setEditable(false);
        cxMarca.setVisible(true);
        janela.add(cxMarca);

        janela.add(new JLabel("Modelo:"));
        JTextField cxModelo = new JTextField(30);
        cxModelo.setEditable(false);
        cxModelo.setVisible(true);
        janela.add(cxModelo);

        janela.add(new JLabel("Cor:"));
        JTextField cxCor = new JTextField(30);
        cxCor.setEditable(false);
        cxCor.setVisible(true);
        janela.add(cxCor);

        janela.add(new JLabel("Velocidade maxima:"));
        JTextField cxVelocMax = new JTextField(30);
        cxVelocMax.setEditable(false);
        cxVelocMax.setVisible(true);
        janela.add(cxVelocMax);

        janela.add(new JLabel("Quantidade rodas:"));
        JTextField cxQtdRodas = new JTextField(10);
        cxQtdRodas.setEditable(false);
        cxQtdRodas.setVisible(true);
        janela.add(cxQtdRodas);

        janela.add(new JLabel("Quantidade de pistoes:"));
        JTextField cxQtdPistoes = new JTextField(13);
        cxQtdPistoes.setEditable(false);
        cxQtdPistoes.setVisible(true);
        janela.add(cxQtdPistoes);

        janela.add(new JLabel("Potencia:"));
        JTextField cxPotencia = new JTextField(30);
        cxPotencia.setEditable(false);
        cxPotencia.setVisible(true);
        janela.add(cxPotencia);

        janela.add(new JLabel("Qtd. Passageiros:"));
        JTextField cxQtdPassageiros = new JTextField(30);
        cxQtdPassageiros.setEditable(false);
        cxQtdPassageiros.setVisible(true);
        janela.add(cxQtdPassageiros);

        JButton btConsultar = new JButton("Consultar");
        btConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Passeio veiculo = encontrarVeiculoPasseio(cxPlaca.getText());

                if (veiculo != null) {
                    cxPlaca.setText(veiculo.getPlaca());
                    cxMarca.setText(veiculo.getMarca());
                    cxModelo.setText(veiculo.getModelo());
                    cxCor.setText(veiculo.getCor());
                    cxVelocMax.setText(String.valueOf(veiculo.getVelocMax()));
                    cxQtdRodas.setText(String.valueOf(veiculo.getQtdRodas()));
                    cxQtdPistoes.setText(String.valueOf(veiculo.getMotor().getQtdPist()));
                    cxPotencia.setText(String.valueOf(veiculo.getMotor().getPotencia()));
                    cxQtdPassageiros.setText(String.valueOf(veiculo.getQtdPassageiros()));
                } else {
                    JOptionPane.showMessageDialog(null, "Placa não existe no sistema", " Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        janela.add(btConsultar);

        JButton btExcluir = new JButton("Excluir");
        btExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean resultado = excluirVeiculoPasseioPlaca(cxPlaca.getText());

                if (resultado) {
                    cxPlaca.setText("");
                    cxMarca.setText("");
                    cxModelo.setText("");
                    cxCor.setText("");
                    cxVelocMax.setText("");
                    cxQtdRodas.setText("");
                    cxQtdPistoes.setText("");
                    cxPotencia.setText("");
                    cxQtdPassageiros.setText("");

                    JOptionPane.showMessageDialog(null, "Veículo de passeio excluído com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro na exclusão do veículo", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        janela.add(btExcluir);

        JButton btSair = new JButton("Sair");
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicialPasseio();
            }
        });
        janela.add(btSair);

        janela.revalidate();
        janela.repaint();
    }

    public static void telaImprimirExcluirPasseioTodos() {
        janela.getContentPane().removeAll();
        janela.setSize(800, 400);
        janela.setLayout(new BorderLayout());
        janela.setTitle("Imprimir/Excluir todos os Veículos de Passeio");

        // colunas da tabela
        String[] colunas = { "Placa", "Marca", "Modelo", "Cor", "Veloc. Máx.", "Qtd. Rodas", "Pistões", "Potencia", "Qtd. Passageiros" };

        // Inicializa tabela vazia
        String[][] dadosVazios = new String[0][colunas.length];
        JTable tabela = new JTable(dadosVazios, colunas);
        JScrollPane scroll = new JScrollPane(tabela);
        janela.add(scroll, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        // Botão Imprimir Todos
        JButton btImprimirTodos = new JButton("Imprimir Todos");
        btImprimirTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Passeio> listaPasseios = imprimirVeiculoPasseioPlaca();

                if (listaPasseios == null || listaPasseios.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum veículo de passeio cadastrado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Preenche os dados da tabela com a lista
                String[][] dados = new String[listaPasseios.size()][20];
                for (int i = 0; i < listaPasseios.size(); i++) {
                    Passeio v = listaPasseios.get(i);
                    dados[i][0] = v.getPlaca();
                    dados[i][1] = v.getMarca();
                    dados[i][2] = v.getModelo();
                    dados[i][3] = v.getCor();
                    dados[i][4] = String.valueOf(v.getVelocMax());
                    dados[i][5] = String.valueOf(v.getQtdRodas());
                    dados[i][6] = String.valueOf(v.getMotor().getQtdPist());
                    dados[i][7] = String.valueOf(v.getMotor().getPotencia());
                    dados[i][8] = String.valueOf(v.getQtdPassageiros());
                }

                // Atualiza a tabela com os novos dados
                tabela.setModel(new DefaultTableModel(dados, colunas));
            }
        });

        JButton btExcluirTodos = new JButton("Excluir Todos");
        btExcluirTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir todos os veículos de passeio?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    bdVeiculos.getListaPasseio().clear();
                    tabela.setModel(new DefaultTableModel(new String[0][colunas.length], colunas));
                    JOptionPane.showMessageDialog(null, "Veículos de passeio excluídos com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton btSair = new JButton("Sair");
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.getContentPane().removeAll();
                telaInicialPasseio();
            }
        });

        painelBotoes.add(btImprimirTodos);
        painelBotoes.add(btExcluirTodos);
        painelBotoes.add(btSair);

        janela.add(painelBotoes, BorderLayout.SOUTH);

        janela.revalidate();
        janela.repaint();
    }
}