package com.medtech;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.medtech.dao.ComponenteDAO;
import com.medtech.dao.UsuarioDAO;
import com.medtech.inovacao.MemoryUsageFinisher;
import com.medtech.model.componente.armazenamento.Armazenamento;
import com.medtech.model.componente.cpu.MonitoramentoCpu;
import com.medtech.model.componente.memoria.MonitoramentoMemoria;
import com.medtech.model.componente.rede.MonitoramentoRede;
import com.medtech.model.usuario.Usuario;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

    private static final String LOG_DIRECTORY = "C:\\ProjectADS\\jar-individual-BeatrizRR";
    private static final String LOG_FILE_PATH = LOG_DIRECTORY + "\\log.txt";

    private static final String EXCEL_FILE_PATH = "C:/ProjectADS/jar-individual-BeatrizRR/dadosColetados.xlsx";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Looca looca = new Looca();
        Armazenamento disco01 = new Armazenamento();
        MonitoramentoMemoria memoria01 = new MonitoramentoMemoria();
        MonitoramentoCpu cpu01 = new MonitoramentoCpu();
        MonitoramentoRede rede = new MonitoramentoRede();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ComponenteDAO componenteDAO = new ComponenteDAO();

        exibirBanner();
        criarArquivoExcel();

        Usuario usuario = autenticarUsuario(scanner, usuarioDAO);
        if (usuario != null && !usuario.getNomeUser().isEmpty()) {
            logEvent("Login bem-sucedido para o usuário: " + usuario.getNomeUser());
            exibirSistema(looca);
            iniciarColetaDeDados(memoria01, cpu01, disco01, rede, componenteDAO, usuario.getNomeUser());
        } else {
            logEvent("Falha no login para o usuário: " + (usuario != null ? usuario.getNomeUser() : "Desconhecido"));
            System.out.println("Usuário ou senha incorretos. Tente novamente mais tarde.");
        }

        scanner.close();
    }

    private static void exibirBanner() {
        System.out.println("""
                 __  __          _ _____         _    
                |  \\/  | ___  __| |_   _|__  ___| |__ 
                | |\\/| |/ _ \\/ _` | | |/ _ \\/ __| '_ \\
                | |  | |  __/ (_| | | |  __/ (__| | | |
                |_|  |_|\\___|\\__,_| |_|\\___|\\___|_| |_|
                                                      """);
        System.out.println("=====================================");
    }

    private static Usuario autenticarUsuario(Scanner scanner, UsuarioDAO usuarioDAO) throws SQLException {
        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senhaUsuario = scanner.nextLine();
        return usuarioDAO.retornaUsuario(nomeUsuario, senhaUsuario);
    }

    private static void exibirSistema(Looca looca) {
        System.out.print("Pegando os dados do seu computador: ");
        for (int i = 0; i <= 25; i++) {
            System.out.print("█");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("] Concluído!");
        Sistema sistema = looca.getSistema();
        logEvent("Dados do sistema recuperados: " + sistema);
        System.out.println(sistema);
        System.out.println("=====================================");
    }

    private static void iniciarColetaDeDados(MonitoramentoMemoria memoria, MonitoramentoCpu cpu, Armazenamento armazenamento, MonitoramentoRede rede, ComponenteDAO componenteDAO, String nomeUsuario) {
        while (true) {
            MemoryUsageFinisher.checkMemoryUsage();
            try {
                Thread.sleep(3000);

                memoria.getMemoriaEmUsoGB();
                cpu.getCpuFreqGHz();
                armazenamento.getVolumes();
                rede.atualizarDadosRede();

                double memoriaEmUso = memoria.getMemoriaEmUsoGB();
                double usoCpuGHz = cpu.getCpuUsoGHz();
                double armazenamentoEmUso = armazenamento.getVolumes();
                double velocidadeRede = rede.calcularVelocidadeRedeMbps();

                componenteDAO.inserirUsoMemoria(memoria, nomeUsuario);
                componenteDAO.inserirUsoArmazenamento(armazenamento, nomeUsuario);
                componenteDAO.inserirUsoCpu(cpu, nomeUsuario);
                componenteDAO.inserirVelocidadeRede(velocidadeRede, cpu.getIdCPU());

                logEvent("Dados coletados: Memória em uso: " + memoriaEmUso + " GB, Uso de CPU: " + usoCpuGHz + " GHz, Armazenamento em uso: " + armazenamentoEmUso + " GB");

                System.out.println("Dados atuais:");
                System.out.println("Uso da Memória: " + String.format("%.2f", memoriaEmUso) + " GB");
                System.out.println("Uso da CPU: " + String.format("%.2f", usoCpuGHz) + " GHz");
                System.out.println("Armazenamento em uso: " + String.format("%.2f", armazenamentoEmUso) + " GB");
                System.out.println("Velocidade da Rede: " + String.format("%.2f", velocidadeRede) + " Mbps");
                System.out.println();

                // Escreve os dados no arquivo Excel
                escreverDadosNoExcel("Uso da Memória", memoriaEmUso, "GB");
                escreverDadosNoExcel("Uso da CPU", usoCpuGHz, "GHz");
                escreverDadosNoExcel("Armazenamento em uso", armazenamentoEmUso, "GB");
                escreverDadosNoExcel("Velocidade da Rede", velocidadeRede, "Mbps");

            } catch (InterruptedException e) {
                e.printStackTrace();
                logEvent("Erro ao coletar dados: " + e.getMessage());
            } catch (SQLException e) {
                logEvent("Erro ao inserir dados no banco de dados: " + e.getMessage());
                System.out.println("Erro ao inserir dados no banco de dados: " + e.getMessage());
            }
        }
    }

    private static void criarArquivoExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dados");

        // Cria o cabeçalho
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Data e Hora");
        headerRow.createCell(1).setCellValue("Tipo de Dado");
        headerRow.createCell(2).setCellValue("Valor");
        headerRow.createCell(3).setCellValue("Medida");

        try (FileOutputStream fileOut = new FileOutputStream(EXCEL_FILE_PATH)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void escreverDadosNoExcel(String tipoDado, double valor, String medida) {
        try {
            Workbook workbook;
            Path path = Paths.get(EXCEL_FILE_PATH);
            if (Files.exists(path)) {
                workbook = WorkbookFactory.create(Files.newInputStream(path));
            } else {
                workbook = new XSSFWorkbook();
            }
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(rowCount + 1);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            row.createCell(0).setCellValue(now.format(formatter));
            row.createCell(1).setCellValue(tipoDado);
            row.createCell(2).setCellValue(valor);
            row.createCell(3).setCellValue(medida);

            try (FileOutputStream fileOut = new FileOutputStream(EXCEL_FILE_PATH)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logEvent(String message) {
        try {
            Path path = Paths.get(LOG_DIRECTORY);

            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = now.format(formatter);

            File log = new File(LOG_FILE_PATH);

            if (!log.exists()) {
                log.createNewFile();
            }

            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(formatDateTime + " - " + message);
            bw.newLine();

            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
