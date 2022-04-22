<?php

namespace App\Repository;

use App\Entity\Categorie2;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Categorie2|null find($id, $lockMode = null, $lockVersion = null)
 * @method Categorie2|null findOneBy(array $criteria, array $orderBy = null)
 * @method Categorie2[]    findAll()
 * @method Categorie2[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class Categorie2Repository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Categorie2::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Categorie2 $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Categorie2 $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    public function findEntitiesByString($str){
        return $this->getEntityManager()
            ->createQuery(
                'SELECT e
                FROM App\Entity\Produit2 e
                WHERE e.NOM LIKE :str'
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }

    // /**
    //  * @return Categorie2[] Returns an array of Categorie2 objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Categorie2
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
